package br.com.manu.service.fornecedor;

import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;

public interface FornecedorService {
    FornecedorResponse create (FornecedorRequest request);
}
