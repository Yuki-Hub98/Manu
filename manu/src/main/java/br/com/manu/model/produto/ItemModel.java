package br.com.manu.model.produto;

public class ItemModel {
    private int idItem;
    private String descricaoItem;
    private String codBarra;
    private String cor;
    private String especificacao;

    public ItemModel() {

    }

    public ItemModel(int idItem, String descricaoItem, String codBarra, String cor, String especificacao) {
        this.idItem = idItem;
        this.descricaoItem = descricaoItem;
        this.codBarra = codBarra;
        this.cor = cor;
        this.especificacao = especificacao;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
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
}
