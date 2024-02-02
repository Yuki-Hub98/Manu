package br.com.manu.service.arvoreProduto.linha;

import br.com.manu.model.arvoreProduto.linha.LinhaDel;
import br.com.manu.model.arvoreProduto.linha.LinhaEdit;
import br.com.manu.model.arvoreProduto.linha.LinhaRequest;
import br.com.manu.model.arvoreProduto.linha.LinhaResponse;

import java.util.List;

public interface LinhaService {
    LinhaResponse create(LinhaRequest request);
    List<LinhaResponse> getAll();
    List<LinhaResponse> getDescricao(String request);
    LinhaResponse edit(LinhaEdit request);
    LinhaDel del(String descricao, LinhaRequest request);
}
