package br.com.manu.service.departamento;

import br.com.manu.model.departamento.DepartamentoRequest;
import br.com.manu.model.departamento.DepartamentoResponse;

import java.util.List;

public interface DepartamentoService {
    DepartamentoResponse create(DepartamentoRequest request);
    List<DepartamentoResponse> getAll();
}
