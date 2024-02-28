package br.com.manu.service.arvoreProduto.familia;

import br.com.manu.model.arvoreProduto.familia.*;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.repository.arvoreProduto.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        Familia familia = new Familia();
        familia.setLinha(resquest.getLinha());
        familia.setDescricao(resquest.getDescricao());
        find(familia).forEach((li) -> {
            if (li.getDescricao().equals(familia.getDescricao()) && li.getLinha().equals(familia.getLinha())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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

    @Override
    public FamiliaResponse edit(FamiliaEdit request) {
        Familia newFamilia = new Familia();
        newFamilia.setLinha(request.getEditLinha());
        newFamilia.setDescricao(request.getEditDescricao());
        find(newFamilia).forEach((li) -> {
            if (li.getDescricao().equals(newFamilia.getDescricao()) && li.getLinha().equals(newFamilia.getLinha())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        List<Grupo> grupos = mongoTemplate.find(Query.query(Criteria.where("familia").is(request.getDescricao())), Grupo.class, "grupo");
        if(!grupos.isEmpty()){
            editFam(request);
            mongoTemplate.updateMulti(Query.query(Criteria.where("familia").is(request.getDescricao())),
                    Update.update("familia", request.getEditDescricao()),
                    Grupo.class, "grupo");
        }else{
           editFam(request);
        }

        return createResponse(newFamilia);
    }

    @Override
    public FamiliaDel del(String descricao, FamiliaResquest request) {
        Familia del = new Familia();
        del.setLinha(request.getLinha());
        del.setDescricao(request.getDescricao());
        List<Grupo> grupos = mongoTemplate.find(Query.query(Criteria.where("familia").is(descricao)), Grupo.class, "grupo");
        if (!grupos.isEmpty()){
            try {
                throw new DataIntegrityViolationException(descricao);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(descricao);
            }
        }else{
            mongoTemplate.remove(Query.query(Criteria.where("linha").is(request.getLinha()).and("descricao").is(request.getDescricao())),
                    Familia.class, "familia");
        }
        return responseDel(del);
    }
    private FamiliaResponse createResponse(Familia familia) {
        FamiliaResponse response = new FamiliaResponse();
        response.setLinha(familia.getLinha());
        response.setDescricao(familia.getDescricao());
        return response;
    }

    private FamiliaResponseLinha createResponseByLinha(Familia familia){
        FamiliaResponseLinha response = new FamiliaResponseLinha();
        response.setLinha(familia.getLinha());
        response.setFamilia(familia.getDescricao());
        return response;
    }
    private FamiliaDel responseDel(Familia familia){
        FamiliaDel response = new FamiliaDel();
        response.setDelLinha(familia.getLinha());
        response.setDel(familia.getDescricao());
        return response;
    }

    private List<Familia> find(Familia campo){
        List<Familia> find = repository.findByname(campo.getLinha(), campo.getDescricao());
        return find;
    }

    private void editFam(FamiliaEdit request){
        mongoTemplate.updateFirst(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Update.update("descricao", request.getEditDescricao()).set("linha", request.getEditLinha()),
                Familia.class, "familia");
    }
}
