package br.com.manu.service.arvoreProduto.linha;

import br.com.manu.model.arvoreProduto.linha.LinhaRequest;
import br.com.manu.model.arvoreProduto.linha.LinhaResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.repository.arvoreProduto.LinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinhaServiceImp implements LinhaService {
    @Autowired
    private LinhaRepository repository;

    @Override
    public LinhaResponse create(LinhaRequest request) {
        Linha linha = new Linha();
        linha.setDepartamento(request.getDepartamento());
        linha.setDescricao(request.getDescricao());
        find(linha).forEach((li) -> {
            if (li.getDescricao().equals(linha.getDescricao()) && li.getDepartamento().equals(linha.getDepartamento())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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

    private LinhaResponse createResponse(Linha linha) {
        LinhaResponse response = new LinhaResponse();
        response.setDepartamento(linha.getDepartamento());
        response.setDescricao(linha.getDescricao());
        return response;
    }

    private List<Linha> find(Linha campo){
        List<Linha> find = repository.findByname(campo.getDepartamento(), campo.getDescricao());
        return find;
    }
}
