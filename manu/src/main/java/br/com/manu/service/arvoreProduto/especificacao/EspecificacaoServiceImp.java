package br.com.manu.service.arvoreProduto.especificacao;

import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoDel;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoRequest;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Especificacao;
import br.com.manu.persistence.entity.produtos.item.Item;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import br.com.manu.persistence.repository.arvoreProduto.EspecificacaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
@Service
public class EspecificacaoServiceImp implements EspecificacaoService{
    @Autowired
    EspecificacaoRespository repository;
    private final MongoTemplate mongoTemplate;

    public EspecificacaoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public EspecificacaoResponse create(EspecificacaoRequest request) {
        Duplicated(request);
        Especificacao especificacao = new Especificacao();
        especificacao.setDescricao(request.getDescricao());
        especificacao.setCodigo(incrementCodigo());
        repository.save(especificacao);
        return createResponse(especificacao);
    }

    @Override
    public List<EspecificacaoResponse> getAll() {
        List<EspecificacaoResponse> responses = new ArrayList<>();
        List<Especificacao> especificacoes = repository.findAll();
        if(!especificacoes.isEmpty()){
            especificacoes.forEach(especificacao -> responses.add(createResponse(especificacao)));
        }
        return responses;
    }

    @Override
    public List<EspecificacaoResponse> getDescricao(String request) {
        List<EspecificacaoResponse> response = new ArrayList<>();
        List<Especificacao> seach = repository.findRegex(request);
        if(!seach.isEmpty()){
            seach.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    /**
     * Recebe o objeto que precisa ser editado, verifica se existe esse objeto em outras collections @item, collections relacionadas
     * com @especificacao, executa um updateMulti na collection @item com a informação nova do objeto.
     * @param codigo
     * @param request
     * @return objeto editado
     */
    @Override
    public EspecificacaoResponse edite(int codigo, EspecificacaoRequest request) {
        Duplicated(request);
        Especificacao newEspecificacao = new Especificacao();
        newEspecificacao.setDescricao(request.getDescricao());
        newEspecificacao.setCodigo(codigo);
        Especificacao especificacaoEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(request.getCodigo())),
                Especificacao.class, "especificacao");
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("especificacao").is(especificacaoEdited.getDescricao())),
                Produto.class, "produto");
        especificacaoEdited(newEspecificacao);
        if (existsInProduto) {
            mongoTemplate.updateMulti(Query.query(Criteria.where("especificacao").is(especificacaoEdited.getDescricao())),
                    Update.update("especificacao", newEspecificacao.getDescricao()),
                    Item.class, "item");
        }

        return createResponse(newEspecificacao);
    }

    /**
     * Antes de deletar, verifica na @item, a collections que está relacionada com @especificacao, se tiver lança um exeception para
     * Avisar que esse documento está sendo utilizado, tem que excluir primeiro nas collections relacionadas para conseguir excluir.
     * @param codigo
     * @param request
     * @return objeto excluido
     */
    @Override
    public EspecificacaoDel del(int codigo, EspecificacaoRequest request) {
        Boolean exists = mongoTemplate.exists(Query.query(Criteria.where("especificacao").is(request.getDescricao())),
                Item.class, "item");
        if (exists) {
            try {
                throw new DataIntegrityViolationException(request.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getDescricao());
            }
        }
        Especificacao del = new Especificacao();
        del.setDescricao(request.getDescricao());
        del.setCodigo(request.getCodigo());
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)),
                Especificacao.class, "especificacao");
        return responseDel(del);
    }

    private EspecificacaoResponse createResponse(Especificacao especificacao) {
        EspecificacaoResponse response = new EspecificacaoResponse();
        response.setCodigo(especificacao.getCodigo());
        response.setDescricao(especificacao.getDescricao());
        return response;
    }

    private void especificacaoEdited(Especificacao newEspecificacao){
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(newEspecificacao.getCodigo())),
                Update.update("descricao", newEspecificacao.getDescricao()), Especificacao.class, "especificacao");
    }

    /**
     * Verifica se existe o objeto na collection @departamento, se tiver lança exception
     * informando que já existe objeto cadastrado
     * @param request
     */
    private void Duplicated (EspecificacaoRequest request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Especificacao.class, "especificacao");
        if(exist) {
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private EspecificacaoDel responseDel(Especificacao especificacao){
        EspecificacaoDel response = new EspecificacaoDel();
        response.setDel(especificacao.getDescricao());
        return response;
    }


    private int incrementCodigo () {
        int id = 0;
        List<Especificacao> prodIds =  mongoTemplate.findAll(Especificacao.class, "especificacao");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Especificacao lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Especificacao.class, "especificacao");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }

}
