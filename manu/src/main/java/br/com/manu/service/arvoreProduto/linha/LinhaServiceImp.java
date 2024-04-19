package br.com.manu.service.arvoreProduto.linha;

import br.com.manu.model.arvoreProduto.linha.*;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import br.com.manu.persistence.repository.arvoreProduto.LinhaRepository;
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
public class LinhaServiceImp implements LinhaService {
    @Autowired
    private LinhaRepository repository;
    private final MongoTemplate mongoTemplate;
    private Departamento departamento;
    public LinhaServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }
    @Override
    public LinhaResponse create(LinhaRequest request) {
        Duplicated(request);
        Linha linha = new Linha();
        linha.setCodigo(incrementCodigo());
        linha.setDepartamento(request.getDepartamento());
        linha.setDescricao(request.getDescricao());
        repository.save(linha);
        return createResponse(linha);

    }

    @Override
    public List<LinhaResponse> getAll() {
        List<LinhaResponse> responses = new ArrayList<>();
        List<Linha> linhas = repository.findAll();
        if(!linhas.isEmpty()){
            linhas.forEach(linha -> responses.add(createResponse(linha)));
        }
        return responses;
    }

    @Override
    public List<LinhaResponse> getDescricao(String request) {
        List<LinhaResponse> response = new ArrayList<>();
        if (request.equals("undefined")){
            List<Linha> linhas = repository.findAll();
            if(!linhas.isEmpty()){
                linhas.forEach(linha -> response.add(createResponse(linha)));
                return response;
            }
        }
        List<Linha> search = repository.findRegex(request);
        if(!search.isEmpty()){
            search.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    @Override
    public List<LinhaResponseDepartamento> getDescricaoByDepartamento(String request) {
        List<LinhaResponseDepartamento> responses = new ArrayList<>();
        List<Linha> descricoes = mongoTemplate.find(Query.query(Criteria.where("departamento").is(request)), Linha.class, "linha");
        descricoes.forEach(linha -> responses.add(createResponseByDepartamento(linha)));
        return responses;
    }

    /**
     * Recebe o objeto que precisa ser editado, verifica se existe esse objeto em outras collections @familia e @produto, collections relacionadas
     * com @linha, executa um updateMulti na collection @familia e @produto com a informação nova do objeto.
     * @param codigo
     * @param request
     * @return objeto editado
     */
    @Override
    public LinhaResponse edit(int codigo, LinhaRequest request) {
        Duplicated(request);
        Linha newLinha = new Linha();
        newLinha.setCodigo(codigo);
        newLinha.setDepartamento(request.getDepartamento());
        newLinha.setDescricao(request.getDescricao());
        Linha linhaEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)),
                Linha.class, "linha");
        Boolean existsInFamilia = mongoTemplate.exists(Query.query(Criteria.where("linha").is(linhaEdited.getDescricao())),
                Familia.class, "familia");
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("linha").is(linhaEdited.getDescricao())),
                Produto.class, "produto");
        editLinha(newLinha);
        if(existsInFamilia){
            mongoTemplate.updateMulti(Query.query(Criteria.where("linha").is(linhaEdited.getDescricao())),
                    Update.update("linha", newLinha.getDescricao()),
                    Familia.class, "familia");
            if (existsInProduto) {
                mongoTemplate.updateMulti(Query.query(Criteria.where("linha").is(linhaEdited.getDescricao())),
                        Update.update("linha", newLinha.getDescricao()),
                        Produto.class, "produto");
            }
        }

        return createResponse(newLinha);
    }

    /**
     * Antes de deletar, verifica na @produto e @familia, duas collections que estõo relacionadas com @linha, se tiver lança um exeception para
     * Avisar que esse documento está sendo utilizado, tem que excluir primeiro nas collections relacionadas para conseguir excluir.
     * @param codigo
     * @param request
     * @return objeto excluido
     */
    @Override
    public LinhaDel del(int codigo, LinhaRequest request) {
        Linha del = new Linha();
        del.setCodigo(codigo);
        del.setDepartamento(request.getDepartamento());
        del.setDescricao(request.getDescricao());
        Boolean existsInFamilia = mongoTemplate.exists(Query.query(Criteria.where("linha").is(del.getDescricao())),
                Familia.class, "familia");
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("linha").is(del.getDescricao())),
                Produto.class, "produto");
        if (existsInFamilia || existsInProduto){
            try {
                throw new DataIntegrityViolationException(del.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(del.getDescricao());
            }
        }else{
            mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)),
                    Linha.class, "linha");
        }
        return responseDel(del);
    }

    private LinhaResponse createResponse(Linha linha) {
        LinhaResponse response = new LinhaResponse();
        response.setCodigo(linha.getCodigo());
        response.setDepartamento(linha.getDepartamento());
        response.setDescricao(linha.getDescricao());
        return response;
    }
    private LinhaResponseDepartamento createResponseByDepartamento(Linha linha){
        LinhaResponseDepartamento response = new LinhaResponseDepartamento();
        response.setCodigo(linha.getCodigo());
        response.setDepartamento(linha.getDepartamento());
        response.setLinha(linha.getDescricao());
        return response;
    }
    private LinhaDel responseDel(Linha linha){
        LinhaDel response = new LinhaDel();
        response.setCodigo(linha.getCodigo());
        response.setDepDel(linha.getDepartamento());
        response.setDel(linha.getDescricao());
        return response;
    }


    private void editLinha (Linha request){
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(request.getCodigo())),
                Update.update("descricao", request.getDescricao()).set("departamento", request.getDepartamento()),
                Linha.class, "linha");
    }

    /**
     * Verifica se o objeto está cadastro na collection @linha, se tiver executa uma exeception
     * informando que já existe cadastro
     * @param request
     */
    private void Duplicated (LinhaRequest request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())
                        .and("departamento").is(request.getDepartamento())), Linha.class, "linha");
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
        List<Linha> prodIds =  mongoTemplate.findAll(Linha.class, "linha");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Linha lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Linha.class, "linha");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
