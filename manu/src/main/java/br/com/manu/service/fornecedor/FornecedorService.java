package br.com.manu.service.fornecedor;

import br.com.manu.model.fornecedor.FornecedorDel;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;

import java.util.List;

public interface FornecedorService {
    FornecedorResponse create (FornecedorRequest request);
    List<FornecedorResponse> getAll();
    List<FornecedorResponse> getParams(String requestName, String requestRazao, String requestCpfCnpj);
    FornecedorResponse edit (int id, FornecedorRequest request);
    FornecedorDel del (int id);
}
