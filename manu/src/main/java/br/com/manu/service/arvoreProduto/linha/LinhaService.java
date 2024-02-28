package br.com.manu.service.arvoreProduto.linha;

import br.com.manu.model.arvoreProduto.linha.*;

import java.util.List;

public interface LinhaService {
    LinhaResponse create(LinhaRequest request);
    List<LinhaResponse> getAll();
    List<LinhaResponse> getDescricao(String request);
    List<LinhaResponseDepartamento> getDescricaoByDepartamento(String request);
    LinhaResponse edit(LinhaEdit request);
    LinhaDel del(String descricao, LinhaRequest request);
}
