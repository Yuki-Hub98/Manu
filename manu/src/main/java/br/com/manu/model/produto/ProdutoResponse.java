package br.com.manu.model.produto;

import br.com.manu.persistence.entity.produtos.item.Item;

import java.util.List;

public class ProdutoResponse {
    private long idProduto;
    private String descricaoProduto;
    private String departamento;
    private String linha;
    private String familia;
    private String grupo;
    private String fornecedor;
    private String modelo;
    private String tipoProduto;
    private String unidadeMedida;
    private List<Item> items;


    public ProdutoResponse() {

    }

    public ProdutoResponse(long idProduto, String descricaoProduto, String departamento, String linha, String familia, String grupo, String fornecedor, String modelo,
                           String tipoProduto, String unidadeMedida, List<Item> items) {
        this.idProduto = idProduto;
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

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
