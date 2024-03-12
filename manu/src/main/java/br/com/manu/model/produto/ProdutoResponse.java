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
    private String processado;
    private String cstIcmsOrigem;
    private String cstIcmsCodigo;
    private String cstIcmsDescricao;
    private String cstPisConfins;
    private String ncmCodigo;
    private String ncmDescricao;
    private List<Item> items;


    public ProdutoResponse() {

    }

    public ProdutoResponse(long idProduto, String descricaoProduto, String departamento, String linha, String familia, String grupo, String fornecedor,
                           String modelo, String tipoProduto, String unidadeMedida, String processado, String cstIcmsOrigem, String cstIcmsCodigo,
                           String cstIcmsDescricao, String cstPisConfins, String ncmCodigo, String ncmDescricao, List<Item> items) {
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
        this.processado = processado;
        this.cstIcmsOrigem = cstIcmsOrigem;
        this.cstIcmsCodigo = cstIcmsCodigo;
        this.cstIcmsDescricao = cstIcmsDescricao;
        this.cstPisConfins = cstPisConfins;
        this.ncmCodigo = ncmCodigo;
        this.ncmDescricao = ncmDescricao;
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

    public String getProcessado() {
        return processado;
    }

    public void setProcessado(String processado) {
        this.processado = processado;
    }

    public String getCstIcmsOrigem() {
        return cstIcmsOrigem;
    }

    public void setCstIcmsOrigem(String cstIcmsOrigem) {
        this.cstIcmsOrigem = cstIcmsOrigem;
    }

    public String getCstIcmsCodigo() {
        return cstIcmsCodigo;
    }

    public void setCstIcmsCodigo(String cstIcmsCodigo) {
        this.cstIcmsCodigo = cstIcmsCodigo;
    }

    public String getCstIcmsDescricao() {
        return cstIcmsDescricao;
    }

    public void setCstIcmsDescricao(String cstIcmsDescricao) {
        this.cstIcmsDescricao = cstIcmsDescricao;
    }

    public String getCstPisConfins() {
        return cstPisConfins;
    }

    public void setCstPisConfins(String cstPisConfins) {
        this.cstPisConfins = cstPisConfins;
    }

    public String getNcmCodigo() {
        return ncmCodigo;
    }

    public void setNcmCodigo(String ncmCodigo) {
        this.ncmCodigo = ncmCodigo;
    }

    public String getNcmDescricao() {
        return ncmDescricao;
    }

    public void setNcmDescricao(String ncmDescricao) {
        this.ncmDescricao = ncmDescricao;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
