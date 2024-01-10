package br.com.manu.service.grupo;

import br.com.manu.model.grupo.GrupoRequest;
import br.com.manu.model.grupo.GrupoResponse;
import br.com.manu.persistence.entity.Grupo;
import br.com.manu.persistence.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GrupoServiceImp implements GrupoService{

    @Autowired
    GrupoRepository repository;

    @Override
    public GrupoResponse create(GrupoRequest request) {
        Grupo grupo = new Grupo();
        grupo.setFamilia(request.getFamilia());
        grupo.setDescricao(request.getDescricao());
        repository.save(grupo);
        return createRequest(grupo);
    }

    @Override
    public List<GrupoResponse> getAll() {
        List<GrupoResponse> responses = new ArrayList<>();
        List<Grupo> grupos = repository.findAll();
        if(!grupos.isEmpty()){
            grupos.forEach(grupo -> responses.add(createRequest(grupo)));
        }
        return responses;
    }

    private GrupoResponse createRequest(Grupo grupo) {
        GrupoResponse response = new GrupoResponse();
        response.setFamilia(grupo.getFamilia());
        response.setDescricao(grupo.getDescricao());
        return response;

    }
}
