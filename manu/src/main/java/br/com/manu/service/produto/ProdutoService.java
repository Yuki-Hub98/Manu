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
    ProdutoResponse getProdutoToEdit(int id);
    lastIdItem getLastId ();
    ResponseItem edit(int id, ProdutoRequest request);
    ProdutoResponse getProduto(String descricaoProduto, String fornecedor);
    List<ProdutoCstIcmsResponse> getCstIcms(String request);
    List<ProdutoNcm> getNcm(String request);
    ProdutoDel del(int id);
    Modelo createModelo(Modelo request);
    List<Modelo> getAllModelos();
    Modelo editModelo(ModeloEdit request);
    ModeloDel delModelo(Modelo request);

}
