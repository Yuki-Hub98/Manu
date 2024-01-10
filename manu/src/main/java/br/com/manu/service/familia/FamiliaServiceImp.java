package br.com.manu.service.familia;

import br.com.manu.model.familia.FamiliaResponse;
import br.com.manu.model.familia.FamiliaResquest;
import br.com.manu.persistence.entity.Familia;
import br.com.manu.persistence.entity.Linha;
import br.com.manu.persistence.repository.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private FamiliaResponse createResponse(Familia familia) {
        FamiliaResponse response = new FamiliaResponse();
        response.setLinha(familia.getLinha());
        response.setDescricao(familia.getDescricao());
        return response;
    }
}
