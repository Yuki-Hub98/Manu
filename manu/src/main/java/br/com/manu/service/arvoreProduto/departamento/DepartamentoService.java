package br.com.manu.service.arvoreProduto.departamento;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;

import java.util.List;

public interface DepartamentoService {
    DepartamentoResponse create(DepartamentoRequest request);
    List<DepartamentoResponse> getAll();
    List<DepartamentoResponse> getDescricao(String request);
}
