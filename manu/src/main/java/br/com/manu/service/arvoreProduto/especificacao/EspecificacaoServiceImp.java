package br.com.manu.service.arvoreProduto.especificacao;

import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoRequest;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Especificacao;
import br.com.manu.persistence.repository.arvoreProduto.EspecificacaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
@Service
public class EspecificacaoServiceImp implements EspecificacaoService{
    @Autowired
    EspecificacaoRespository repository;

    @Override
    public EspecificacaoResponse create(EspecificacaoRequest request) {
        Especificacao especificacao = new Especificacao();
        especificacao.setDescricao(request.getDescricao());
        find(especificacao).forEach(dep -> {
            if(dep.getDescricao().equals(especificacao.getDescricao())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        repository.save(especificacao);
        return createResponse(especificacao);
    }

    @Override
    public List<EspecificacaoResponse> getAll() {
        List<EspecificacaoResponse> responses = new ArrayList<>();
        List<Especificacao> especificacoes = repository.findAll();
        if(!especificacoes.isEmpty()){
            especificacoes.forEach(especificacao -> responses.add(createResponse(especificacao)));
        }
        return responses;
    }
    private EspecificacaoResponse createResponse(Especificacao especificacao) {
        EspecificacaoResponse response = new EspecificacaoResponse();
        response.setDescricao(especificacao.getDescricao());
        return response;
    }

    private List<Especificacao> find(Especificacao campo){
        List<Especificacao> find = repository.findByname(campo.getDescricao());
        return find;
    }

}
