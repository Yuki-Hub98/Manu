package br.com.manu.service.arvoreProduto.cor;

import br.com.manu.model.arvoreProduto.cor.CorDel;
import br.com.manu.model.arvoreProduto.cor.CorRequest;
import br.com.manu.model.arvoreProduto.cor.CorResponse;

import java.util.List;

public interface CorService {
    CorResponse create(CorRequest request);
    List<CorResponse> getAll();
    List<CorResponse> getDescricao(String request);
    CorResponse edit(int codigo, CorRequest request);
    CorDel del(int codigo, CorRequest request);
}
