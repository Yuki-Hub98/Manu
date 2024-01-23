package br.com.manu.service.arvoreProduto.cor;

import br.com.manu.model.arvoreProduto.cor.CorRequest;
import br.com.manu.model.arvoreProduto.cor.CorResponse;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Cor;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.repository.arvoreProduto.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CorServiceImp implements CorService{
    @Autowired
    private CorRepository repository;

    @Override
    public CorResponse create(CorRequest request) {
        Cor cor = new Cor();
        cor.setDescricao(request.getDescricao());
        find(cor).forEach(dep -> {
            if(dep.getDescricao().equals(cor.getDescricao())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
        List<Cor> seach = repository.findRegex(request);
        if(!seach.isEmpty()){
            seach.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    private CorResponse createResponse(Cor cor) {
        CorResponse response = new CorResponse();
        response.setDescricao(cor.getDescricao());
        return response;
    }

    private List<Cor> find(Cor campo){
        List<Cor> find = repository.findByname(campo.getDescricao());
        return find;
    }
}
