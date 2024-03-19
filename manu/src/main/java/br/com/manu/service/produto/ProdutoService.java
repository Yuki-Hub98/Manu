package br.com.manu.service.produto;

import br.com.manu.model.produto.*;

import java.util.List;


public interface ProdutoService {
    ProdutoResponse create(ProdutoRequest request);

    List<ResponseItem> getAll();

    List<ResponseItem> getParams( String idItem, String descricaoItem, String codBarra,String departamento,
                                  String linha,String familia,String grupo,String fornecedor,String modelo,
                                  String tipoProduto,String unidadeMedida,String cor,String especificacao);
    List<ModeloProduto> getModels(String linha);

    lastIdItem getLastId ();
    ResponseItem edit(int id, ItemsRequestParams request);
    ProdutoResponse getProduto(String descricaoProduto, String fornecedor);
    List<ProdutoCstIcmsResponse> getCstIcms(String request);
    List<ProdutoNcm> getNcm(String request);
    ProdutoDel del(int id);

}
