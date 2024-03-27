package br.com.manu.service.arvoreProduto.especificacao;

import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoDel;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoRequest;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoResponse;

import java.util.List;

public interface EspecificacaoService {

    EspecificacaoResponse create(EspecificacaoRequest request);
    List<EspecificacaoResponse> getAll();
    List<EspecificacaoResponse> getDescricao(String request);
    EspecificacaoResponse edite(int codigo, EspecificacaoRequest request);
    EspecificacaoDel del(int codigo, EspecificacaoRequest request);
}
