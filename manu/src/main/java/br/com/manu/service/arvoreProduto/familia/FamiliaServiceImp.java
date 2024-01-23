package br.com.manu.service.arvoreProduto.familia;

import br.com.manu.model.arvoreProduto.familia.FamiliaResponse;
import br.com.manu.model.arvoreProduto.familia.FamiliaResquest;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.repository.arvoreProduto.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
@Service
public class FamiliaServiceImp implements FamiliaService{

    @Autowired
    private FamiliaRepository repository;
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

    private FamiliaResponse createResponse(Familia familia) {
        FamiliaResponse response = new FamiliaResponse();
        response.setLinha(familia.getLinha());
        response.setDescricao(familia.getDescricao());
        return response;
    }

    private List<Familia> find(Familia campo){
        List<Familia> find = repository.findByname(campo.getLinha(), campo.getDescricao());
        return find;
    }
}
