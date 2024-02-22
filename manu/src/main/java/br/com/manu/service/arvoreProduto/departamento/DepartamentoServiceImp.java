package br.com.manu.service.arvoreProduto.departamento;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoDel;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoEdit;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.repository.arvoreProduto.DepartamentoRepository;
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
public class DepartamentoServiceImp implements DepartamentoService{
    @Autowired
    private DepartamentoRepository repository;

    private final MongoTemplate mongoTemplate;
    public DepartamentoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }

    @Override
    public DepartamentoResponse create(DepartamentoRequest request) {
        Departamento departamento = new Departamento();
        departamento.setDescricao(request.getDescricao());
        find(departamento).forEach(dep -> {
            if(dep.getDescricao().equals(departamento.getDescricao())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        repository.save(departamento);
        return createResponse(departamento);
        }

    @Override
    public List<DepartamentoResponse> getAll() {
        List<DepartamentoResponse> responses = new ArrayList<>();
        List<Departamento> departamentos = repository.findAll();
        if(!departamentos.isEmpty()){
            departamentos.forEach(departamento -> responses.add(createResponse(departamento)));
        }
        return responses;
    }

    @Override
    public List<DepartamentoResponse> getDescricao(String request) {
        List<DepartamentoResponse> response = new ArrayList<>();
        List<Departamento> seach = repository.findRegex(request);
        if(!seach.isEmpty()){
            seach.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    @Override
    public DepartamentoResponse edit(DepartamentoEdit request) {
        Departamento newDepartamento = new Departamento();
        newDepartamento.setDescricao(request.getEdit());
        find(newDepartamento).forEach(newDep -> {
            if(newDep.getDescricao().equals(newDepartamento.getDescricao())){
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        List<Linha> linhas = mongoTemplate.find(Query.query(Criteria.where("departamento").is(request.getDescricao())), Linha.class, "linha");
        if(!linhas.isEmpty()){
            editDep(request);
            mongoTemplate.updateMulti(Query.query(Criteria.where("departamento").is(request.getDescricao())),
                    Update.update("departamento", request.getEdit()),
                    Linha.class, "linha");
            linhas.forEach(System.out::println);
        }else {
            editDep(request);
        }

        return createResponse(newDepartamento);
    }

    @Override
    public DepartamentoDel del(DepartamentoRequest request) throws DataIntegrityViolationException {
        Departamento del = new Departamento();
        del.setDescricao(request.getDescricao());
        List<Linha> linhas = mongoTemplate.find(Query.query(Criteria.where("departamento").is(request.getDescricao())), Linha.class, "linha");
        if(!linhas.isEmpty()){
            try {
                throw new DataIntegrityViolationException(request.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getDescricao());
            }
        }else{
            mongoTemplate.remove(Query.query(Criteria.where("descricao").is(request.getDescricao())), Departamento.class, "departamento");
        }
        return responseDel(del);
    }


    private DepartamentoResponse createResponse(Departamento departamento) {
        DepartamentoResponse response = new DepartamentoResponse();
        response.setDescricao(departamento.getDescricao());
        return response;

    }

    private DepartamentoDel responseDel(Departamento departamento){
        DepartamentoDel response = new DepartamentoDel();
        response.setDel(departamento.getDescricao());
        return response;
    }

    private List<Departamento> find(Departamento campo){
        List<Departamento> find = repository.findByname(campo.getDescricao());
        return find;
    }

    private void editDep (DepartamentoEdit request){
        mongoTemplate.updateFirst(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Update.update("descricao", request.getEdit()),
                Departamento.class, "departamento");
    }

}
