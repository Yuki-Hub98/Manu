package br.com.manu.service.produto;

import br.com.manu.model.produto.*;

import java.util.List;


public interface ProdutoService {
    ProdutoResponse create(ProdutoRequest request);

    List<ResponseItem> getAll();

    List<ProdutoResponse> getParams(ProdutoRequestParams request);
    ProdutoDel del(int id);

}
