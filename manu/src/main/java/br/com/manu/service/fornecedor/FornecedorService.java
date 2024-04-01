package br.com.manu.service.fornecedor;

import br.com.manu.model.fornecedor.FornecedorDel;
import br.com.manu.model.fornecedor.FornecedorName;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;
import org.apache.coyote.Request;

import java.util.List;

public interface FornecedorService {
    FornecedorResponse create (FornecedorRequest request);
    List<FornecedorResponse> getAll();
    List<FornecedorResponse> getParams(String requestName, String requestRazao, String requestCpfCnpj);
    List<FornecedorName> getNameFantasiaFornecedor();
    FornecedorResponse edit (int id, FornecedorRequest request);
    FornecedorResponse del (int id, FornecedorRequest Request);
}
