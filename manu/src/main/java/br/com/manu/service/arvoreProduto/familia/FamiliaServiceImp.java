package br.com.manu.service.arvoreProduto.familia;

import br.com.manu.model.arvoreProduto.familia.*;
import br.com.manu.model.arvoreProduto.linha.LinhaRequest;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import br.com.manu.persistence.repository.arvoreProduto.FamiliaRepository;
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
public class FamiliaServiceImp implements FamiliaService{

    @Autowired
    private FamiliaRepository repository;
    private final MongoTemplate mongoTemplate;
    private Linha linha;
    public FamiliaServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }
    @Override
    public FamiliaResponse create(FamiliaResquest resquest) {
        Duplicated(resquest);
        Familia familia = new Familia();
        familia.setCodigo(incrementCodigo());
        familia.setLinha(resquest.getLinha());
        familia.setDescricao(resquest.getDescricao());
        repository.save(familia);
        return createResponse(familia);
    }

    @Override
    public List<FamiliaResponse> getAll() {
        List<FamiliaResponse> responses = new ArrayList<>();
        List<Familia> familias = repository.findAll();
        if(!familias.isEmpty()){
            familias.forEach(familia -> responses.add(createResponse(familia)));
        }
        return responses;
    }

    @Override
    public List<FamiliaResponse> getDescricao(String request) {
        List<FamiliaResponse> response = new ArrayList<>();
        if (request.equals("undefined")){
            List<Familia> familias = repository.findAll();
            if(!familias.isEmpty()){
                familias.forEach(familia -> response.add(createResponse(familia)));
                return response;
            }
        }
        List<Familia> search = repository.findRegex(request);
        if(!search.isEmpty()){
            search.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    @Override
    public List<FamiliaResponseLinha> getFamiliaByLinha(String request) {
        List<FamiliaResponseLinha> responses = new ArrayList<>();
        List<Familia> familias = mongoTemplate.find(Query.query(Criteria.where("linha").is(request)), Familia.class, "familia");
        familias.forEach(familia -> responses.add(createResponseByLinha(familia)));
        return responses;
    }
    /**
     * Recebe o objeto que precisa ser editado, verifica se existe esse objeto em outras collections @grupo e @produto, collections relacionadas
     * com @Familia, executa um updateMulti na collection @grupo e @produto com a informação nova  do objeto.
     * @param codigo
     * @param request
     * @return objeto editado
     **/
    @Override
    public FamiliaResponse edit(int codigo, FamiliaResquest request) {
        Duplicated(request);
        Familia newFamilia = new Familia();
        newFamilia.setCodigo(codigo);
        newFamilia.setLinha(request.getLinha());
        newFamilia.setDescricao(request.getDescricao());
       Familia familiaEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)),
               Familia.class, "familia");
       Boolean existsInGrupo = mongoTemplate.exists(Query.query(Criteria.where("familia").is(familiaEdited.getDescricao())),
               Grupo.class, "grupo");
       Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("familia").is(familiaEdited.getDescricao())),
               Produto.class, "produto");
        editFamilia(newFamilia);
        if(existsInGrupo){
            mongoTemplate.updateMulti(Query.query(Criteria.where("familia").is(familiaEdited.getDescricao())),
                    Update.update("familia", newFamilia.getDescricao()),
                    Grupo.class, "grupo");
            if(existsInProduto){
                mongoTemplate.updateMulti(Query.query(Criteria.where("familia").is(familiaEdited.getDescricao())),
                        Update.update("familia", newFamilia.getDescricao()),
                        Produto.class, "produto");
            }
        }

        return createResponse(newFamilia);
    }
    /**
     * Antes de deletar, verifica na @grupo e @produto, duas collections que estõo relacionadas com @familia, se tiver lança um exeception para
     * Avisar que esse documento está sendo utilizado, tem que excluir primeiro nas collections relacionadas para conseguir excluir.
     * @param codigo
     * @param request
     * @return objeto excluido
     **/
    @Override
    public FamiliaDel del(int codigo, FamiliaResquest request) {
        Familia del = new Familia();
        del.setCodigo(codigo);
        del.setLinha(request.getLinha());
        del.setDescricao(request.getDescricao());
        Boolean existsInGrupo = mongoTemplate.exists(Query.query(Criteria.where("familia").is(del.getDescricao())),
                Grupo.class, "grupo");
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("familia").is(del.getDescricao())),
                Produto.class, "produto");
        if (existsInGrupo || existsInProduto){
            try {
                throw new DataIntegrityViolationException(del.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(del.getDescricao());
            }
        }else{
            mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)),
                    Familia.class, "familia");
        }
        return responseDel(del);
    }
    private FamiliaResponse createResponse(Familia familia) {
        FamiliaResponse response = new FamiliaResponse();
        response.setCodigo(familia.getCodigo());
        response.setLinha(familia.getLinha());
        response.setDescricao(familia.getDescricao());
        return response;
    }

    private FamiliaResponseLinha createResponseByLinha(Familia familia){
        FamiliaResponseLinha response = new FamiliaResponseLinha();
        response.setCodigo(familia.getCodigo());
        response.setLinha(familia.getLinha());
        response.setFamilia(familia.getDescricao());
        return response;
    }
    private FamiliaDel responseDel(Familia familia){
        FamiliaDel response = new FamiliaDel();
        response.setCodigo(familia.getCodigo());
        response.setDelLinha(familia.getLinha());
        response.setDel(familia.getDescricao());
        return response;
    }

    private void editFamilia(Familia request){
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(request.getCodigo())),
                Update.update("descricao", request.getDescricao()).set("linha", request.getLinha()),
                Familia.class, "familia");
    }

    /**
     * Verifica se existe o objeto na collection @familia, se tiver lança exception
     * informando que existe objeto cadastrado
     * @param request
     */
    private void Duplicated (FamiliaResquest request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())
                        .and("linha").is(request.getLinha())), Familia.class, "familia");
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
        List<Familia> prodIds =  mongoTemplate.findAll(Familia.class, "familia");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Familia lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Familia.class, "familia");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
