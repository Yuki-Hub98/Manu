package br.com.manu.model.produto;

import java.util.List;

public class ProdutoRequest {
    private String descricaoProduto;
    private String departamento;
    private String linha;
    private String familia;
    private String grupo;
    private String fornecedor;
    private String modelo;
    private String tipoProduto;
    private String unidadeMedida;
    private List<ItemModel> items;


    public ProdutoRequest() {

    }

    public ProdutoRequest(String descricaoProduto, String departamento, String linha, String familia, String grupo, String fornecedor, String modelo,
                          String tipoProduto, String unidadeMedida, List<ItemModel> items) {
        this.descricaoProduto = descricaoProduto;
        this.departamento = departamento;
        this.linha = linha;
        this.familia = familia;
        this.grupo = grupo;
        this.fornecedor = fornecedor;
        this.modelo = modelo;
        this.tipoProduto = tipoProduto;
        this.unidadeMedida = unidadeMedida;
        this.items = items;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
}
