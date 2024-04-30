package br.com.manu.service.arvoreProduto.cor;

import br.com.manu.model.arvoreProduto.cor.CorDel;
import br.com.manu.model.arvoreProduto.cor.CorRequest;
import br.com.manu.model.arvoreProduto.cor.CorResponse;
import br.com.manu.persistence.entity.arvoreProduto.Cor;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.entity.produtos.item.Item;
import br.com.manu.persistence.repository.arvoreProduto.CorRepository;
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
public class CorServiceImp implements CorService{
    @Autowired
    private CorRepository repository;
    private final MongoTemplate mongoTemplate;

    public CorServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }

    @Override
    public CorResponse create(CorRequest request) {
        Duplicated(request);
        Cor cor = new Cor();
        cor.setDescricao(request.getDescricao());
        cor.setCodigo(incrementCodigo());
        repository.save(cor);
        return createResponse(cor);
    }

    @Override
    public List<CorResponse> getAll() {
        List<CorResponse> responses = new ArrayList<>();
        List<Cor> cors = repository.findAll();
        if(!cors.isEmpty()){
            cors.forEach(cor -> responses.add(createResponse(cor)));
        }
        return responses;
    }

    @Override
    public List<CorResponse> getDescricao(String request) {
        List<CorResponse> response = new ArrayList<>();
        if (request.equals("undefined")){
            List<Cor> cors = repository.findAll();
            if(!cors.isEmpty()){
                cors.forEach(cor -> response.add(createResponse(cor)));
                return response;
            }
        }
        List<Cor> seach = repository.findRegex(request);
        if(!seach.isEmpty()){
            seach.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    /**
     * Recebe o objeto que precisa ser editado, verifica se existe esse objeto em outras collections @item, collections relacionadas
     * com @cor, executa um updateMulti na collection @item com a informação nova do objeto.
     * @param codigo
     * @param request
     * @return objeto editado
     **/
    @Override
    public CorResponse edit(int codigo, CorRequest request) {
        Duplicated(request);
        Cor newCor = new Cor();
        newCor.setDescricao(request.getDescricao());
        newCor.setCodigo(request.getCodigo());
        Cor corEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(request.getCodigo())),
                Cor.class, "cor");
        Boolean existsInItem = mongoTemplate.exists(Query.query(Criteria.where("cor").is(corEdited.getDescricao())), Item.class, "item");
        editCor(newCor);
        if(existsInItem) {
            mongoTemplate.updateMulti(Query.query(Criteria.where("cor").is(corEdited.getDescricao())),
                    Update.update("cor", newCor.getDescricao()),
                    Item.class, "item");
        }

        return createResponse(newCor);
    }

    /**
     * Antes de deletar, verifica na @item, a collections que está relacionada com @cor, se tiver lança um exeception para
     * Avisar ar que esse documento está sendo utilizado, tem que excluir primeiro nas collections relacionadas para conseguir excluir.
     * @param codigo
     * @param request
     * @return objeto excluido
     */
    @Override
    public CorDel del(int codigo, CorRequest request) {
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("cor").is(request.getDescricao())),
                Item.class, "item");
        if (exist) {
            try {
                throw new DataIntegrityViolationException(request.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getDescricao());
            }
        }
        Cor del = new Cor();
        del.setCodigo(codigo);
        del.setDescricao(request.getDescricao());
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)), Cor.class, "cor");
        return responseDel(del);
    }

    private CorResponse createResponse(Cor cor) {
        CorResponse response = new CorResponse();
        response.setCodigo(cor.getCodigo());
        response.setDescricao(cor.getDescricao());
        return response;
    }

    private CorDel responseDel(Cor cor){
        CorDel del = new CorDel();
        del.setCodigo(cor.getCodigo());
        del.setDel(cor.getDescricao());
        return del;
    }

    private void editCor(Cor newCor){
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(newCor.getCodigo())),
                Update.update("descricao", newCor.getDescricao()), Cor.class, "cor");
    }

    /**
     * Verifica se existe o objeto na collection @cor, se tiver lança exception
     * informando que já existe objeto cadastrado
     * @param request
     */
    private void Duplicated (CorRequest request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Cor.class, "cor");
        if(exist) {
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int incrementCodigo () {
        int id = 0;
        List<Cor> prodIds =  mongoTemplate.findAll(Cor.class, "cor");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Cor lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Cor.class, "cor");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
