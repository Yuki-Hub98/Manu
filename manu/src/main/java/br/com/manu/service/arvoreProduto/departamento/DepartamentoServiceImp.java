package br.com.manu.service.arvoreProduto.departamento;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.repository.arvoreProduto.DepartamentoRepository;
import br.com.manu.service.exceptions.Exe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartamentoServiceImp implements DepartamentoService{
    @Autowired
    private DepartamentoRepository repository;
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
