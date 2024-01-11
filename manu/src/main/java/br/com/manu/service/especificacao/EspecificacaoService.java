package br.com.manu.service.especificacao;

import br.com.manu.model.especificacao.EspecificacaoRequest;
import br.com.manu.model.especificacao.EspecificacaoResponse;

import java.util.List;

public interface EspecificacaoService {

    EspecificacaoResponse create(EspecificacaoRequest request);
    List<EspecificacaoResponse> getAll();
}
