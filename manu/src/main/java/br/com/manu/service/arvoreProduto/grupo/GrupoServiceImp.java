package br.com.manu.service.arvoreProduto.grupo;

import br.com.manu.model.arvoreProduto.grupo.*;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import br.com.manu.persistence.repository.arvoreProduto.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Grupo grupo = new Grupo();
        grupo.setFamilia(request.getFamilia());
        grupo.setDescricao(request.getDescricao());
        find(grupo).forEach((li) -> {
            if (li.getDescricao().equals(grupo.getDescricao()) && li.getFamilia().equals(grupo.getFamilia())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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

    @Override
    public GrupoResponse edit(GrupoEdit request) {
        Grupo newGrupo = new Grupo();
        newGrupo.setFamilia(request.getEditFamilia());
        newGrupo.setDescricao(request.getEditDescricao());
        find(newGrupo).forEach((li) -> {
            if (li.getDescricao().equals(newGrupo.getDescricao()) && li.getFamilia().equals(newGrupo.getFamilia())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        mongoTemplate.updateFirst(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Update.update("familia", request.getEditFamilia()).set("descricao", request.getEditDescricao()),
                Grupo.class, "grupo");

        return createResponse(newGrupo);
    }

    @Override
    public GrupoDel del(String descricao, GrupoRequest request) {
        Grupo del = new Grupo();
        del.setDescricao(request.getDescricao());
        del.setFamilia(request.getFamilia());
        mongoTemplate.remove(Query.query(Criteria.where("familia").is(request.getFamilia()).and("descricao").is(request.getDescricao())),
                Grupo.class, "grupo");
        return responseDel(del);
    }

    private GrupoResponse createResponse(Grupo grupo) {
        GrupoResponse response = new GrupoResponse();
        response.setFamilia(grupo.getFamilia());
        response.setDescricao(grupo.getDescricao());
        return response;

    }

    private GrupoResponseFamilia createResponseByFamilia(Grupo grupo){
        GrupoResponseFamilia response = new GrupoResponseFamilia();
        response.setFamilia(grupo.getFamilia());
        response.setGrupo(grupo.getDescricao());
        return response;
    }
    private GrupoDel responseDel(Grupo grupo){
        GrupoDel response = new GrupoDel();
        response.setDel(grupo.getDescricao());
        response.setDelFam(grupo.getFamilia());
        return response;
    }
    private List<Grupo> find(Grupo campo){
        List<Grupo> find = repository.findByname(campo.getFamilia(), campo.getDescricao());
        return find;
    }
}
