package br.com.manu.service.arvoreProduto.departamento;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoEdit;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.repository.arvoreProduto.DepartamentoRepository;
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
public class DepartamentoServiceImp implements DepartamentoService{
    @Autowired
    private DepartamentoRepository repository;

    private MongoTemplate mongoTemplate;
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

        mongoTemplate.updateFirst(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Update.update("descricao", request.getEdit()),
                Departamento.class, "departamento");

        return createResponse(newDepartamento);
    }


    private DepartamentoResponse createResponse(Departamento departamento) {
        DepartamentoResponse response = new DepartamentoResponse();
        response.setDescricao(departamento.getDescricao());
        return response;

    }

    private List<Departamento> find(Departamento campo){
        List<Departamento> find = repository.findByname(campo.getDescricao());
        return find;
    }
}
