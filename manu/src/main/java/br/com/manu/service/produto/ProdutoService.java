package br.com.manu.service.produto;

import br.com.manu.model.produto.*;

import java.util.List;


public interface ProdutoService {
    List<ResponseItem> create(ProdutoRequest request);
    List<ResponseItem> getAll();
    List<ResponseItem> getParams( String idItem, String descricaoItem, String codBarra,String departamento,
                                  String linha,String familia,String grupo,String fornecedor,String modelo,
                                  String tipoProduto,String unidadeMedida,String cor,String especificacao);
    List<ModeloProduto> getModels(String linha);
    ProdutoResponse getProdutoToEdit(int id);
    lastIdItem getLastId ();
    List<ResponseItem> edit(int id, ProdutoRequest request);
    ProdutoResponse getProduto(String descricaoProduto, String fornecedor);
    List<ProdutoCstIcmsResponse> getCstIcms(String request);
    List<ProdutoNcm> getNcm(String request);
    ResponseItem del(int id, ResponseItem item);
    Modelo createModelo(Modelo request);
    List<Modelo> getAllModelos();
    Modelo editModelo(ModeloEdit request);
    ModeloDel delModelo(Modelo request);

}
