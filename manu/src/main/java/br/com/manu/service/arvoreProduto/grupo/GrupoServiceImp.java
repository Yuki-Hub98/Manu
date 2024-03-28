package br.com.manu.service.arvoreProduto.grupo;

import br.com.manu.model.arvoreProduto.familia.FamiliaResquest;
import br.com.manu.model.arvoreProduto.grupo.*;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import br.com.manu.persistence.repository.arvoreProduto.GrupoRepository;
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
public class GrupoServiceImp implements GrupoService{

    @Autowired
    GrupoRepository repository;
    private final MongoTemplate mongoTemplate;
    public GrupoServiceImp(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public GrupoResponse create(GrupoRequest request) {
        Duplicated(request);
        Grupo grupo = new Grupo();
        grupo.setCodigo(incrementCodigo());
        grupo.setFamilia(request.getFamilia());
        grupo.setDescricao(request.getDescricao());
        repository.save(grupo);
        return createResponse(grupo);
    }

    @Override
    public List<GrupoResponse> getAll() {
        List<GrupoResponse> responses = new ArrayList<>();
        List<Grupo> grupos = repository.findAll();
        if(!grupos.isEmpty()){
            grupos.forEach(grupo -> responses.add(createResponse(grupo)));
        }
        return responses;
    }

    @Override
    public List<GrupoResponse> getDescricao(String request) {
        List<GrupoResponse> response = new ArrayList<>();
        List<Grupo> search = repository.findRegex(request);
        if(!search.isEmpty()){
            search.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    @Override
    public List<GrupoResponseFamilia> getGrupoByFamilia(String request) {
        List<GrupoResponseFamilia> responses = new ArrayList<>();
        List<Grupo> grupos = mongoTemplate.find(Query.query(Criteria.where("familia").is(request)), Grupo.class, "grupo");
        grupos.forEach(grupo -> responses.add(createResponseByFamilia(grupo)));
        return responses;
    }

    /**
     * Recebe o objeto que precisa ser editado, verifica se existe esse objeto em outras collections @produto, collections relacionadas
     * com @Grupo, executa um updateMulti na collection @produto com a informação nova do objeto.
     * @param codigo
     * @param request
     * @return objeto editado
     */
    @Override
    public GrupoResponse edit(int codigo, GrupoRequest request) {
        Duplicated(request);
        Grupo newGrupo = new Grupo();
        newGrupo.setCodigo(codigo);
        newGrupo.setFamilia(request.getFamilia());
        newGrupo.setDescricao(request.getDescricao());
        Grupo grupoEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)),
                Grupo.class, "grupo");
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("grupo").is(grupoEdited.getDescricao())),
                Produto.class, "produto");
        editGrupo(newGrupo);
        if (existsInProduto){
            mongoTemplate.updateMulti(Query.query(Criteria.where("grupo").is(grupoEdited.getDescricao())),
                    Update.update("grupo", newGrupo.getDescricao()),
                    Produto.class, "produto");
        }
        return createResponse(newGrupo);
    }

    /**
     * Antes de deletar, verifica na @produto, collections que está relacionada com @grupo, se tiver lança um exeception para
     * Avisar que esse documento está sendo utilizado, tem que excluir primeiro nas collections relacionadas para conseguir excluir.
     * @param codigo
     * @param request
     * @return objeto excluido
     */
    @Override
    public GrupoDel del(int codigo, GrupoRequest request) {
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("grupo").is(request.getDescricao())),
                Produto.class, "produto");
        if(existsInProduto){
            try {
                throw new DataIntegrityViolationException(request.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getDescricao());
            }
        }
        Grupo del = new Grupo();
        del.setCodigo(codigo);
        del.setDescricao(request.getDescricao());
        del.setFamilia(request.getFamilia());
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)),
                Grupo.class, "grupo");
        return responseDel(del);
    }

    private GrupoResponse createResponse(Grupo grupo) {
        GrupoResponse response = new GrupoResponse();
        response.setCodigo(grupo.getCodigo());
        response.setFamilia(grupo.getFamilia());
        response.setDescricao(grupo.getDescricao());
        return response;

    }

    private GrupoResponseFamilia createResponseByFamilia(Grupo grupo){
        GrupoResponseFamilia response = new GrupoResponseFamilia();
        response.setCodigo(grupo.getCodigo());
        response.setFamilia(grupo.getFamilia());
        response.setGrupo(grupo.getDescricao());
        return response;
    }
    private GrupoDel responseDel(Grupo grupo){
        GrupoDel response = new GrupoDel();
        response.setCodigo(grupo.getCodigo());
        response.setDel(grupo.getDescricao());
        response.setDelFamilia(grupo.getFamilia());
        return response;
    }
    private void editGrupo(Grupo grupo){
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(grupo.getCodigo())),
                Update.update("familia", grupo.getFamilia()).set("descricao", grupo.getDescricao()),
                Grupo.class, "grupo");
    }

    /**
     * Verifica se existe o objeto na collection @grupo, se tiver lança exception
     * informando que já existe um objeto cadastrado
     * @param request
     */
    private void Duplicated (GrupoRequest request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())
                .and("familia").is(request.getFamilia())), Grupo.class, "grupo");
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
        List<Grupo> prodIds =  mongoTemplate.findAll(Grupo.class, "grupo");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Grupo lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Grupo.class, "grupo");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
