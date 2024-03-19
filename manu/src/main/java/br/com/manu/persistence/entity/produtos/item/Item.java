package br.com.manu.persistence.entity.produtos.item;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class Item {
    @Id
    private String id;
    private long idItem;
    private String descricaoItem;
    private String codBarra;
    private String cor;
    private String especificacao;
    private String _idProduto;
    private String fornecedor;
    @CreatedDate
    private Date dataCriacao;
    @LastModifiedDate
    private Date dataModificacao;
    public Item() {

    }

    public Item(String id, long idItem, String descricaoItem, String codBarra, String cor, String especificacao, String _idProduto, String fornecedor, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.idItem = idItem;
        this.descricaoItem = descricaoItem;
        this.codBarra = codBarra;
        this.cor = cor;
        this.especificacao = especificacao;
        this._idProduto = _idProduto;
        this.fornecedor = fornecedor;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public String get_idProduto() {
        return _idProduto;
    }

    public void set_idProduto(String _idProduto) {
        this._idProduto = _idProduto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public Date getDataModificacao() {
        return dataModificacao;
    }
    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
