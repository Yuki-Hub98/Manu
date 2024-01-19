package br.com.manu.service.arvoreProduto.grupo;

import br.com.manu.model.arvoreProduto.grupo.GrupoRequest;
import br.com.manu.model.arvoreProduto.grupo.GrupoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import br.com.manu.persistence.repository.arvoreProduto.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
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
        find(grupo).forEach((li) -> {
            if (li.getDescricao().equals(grupo.getDescricao()) && li.getFamilia().equals(grupo.getFamilia())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
    private List<Grupo> find(Grupo campo){
        List<Grupo> find = repository.findByname(campo.getFamilia(), campo.getDescricao());
        return find;
    }
}
