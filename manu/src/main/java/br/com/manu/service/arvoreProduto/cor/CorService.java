package br.com.manu.service.arvoreProduto.cor;

import br.com.manu.model.arvoreProduto.cor.CorRequest;
import br.com.manu.model.arvoreProduto.cor.CorResponse;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;

import java.util.List;

public interface CorService {
    CorResponse create(CorRequest request);
    List<CorResponse> getAll();
    List<CorResponse> getDescricao(CorRequest request);
}
