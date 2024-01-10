package br.com.manu.service.Cor;

import br.com.manu.model.cor.CorRequest;
import br.com.manu.model.cor.CorResponse;
import br.com.manu.persistence.entity.Cor;
import br.com.manu.persistence.repository.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private CorResponse createResponse(Cor cor) {
        CorResponse response = new CorResponse();
        response.setDescricao(cor.getDescricao());
        return response;
    }
}
