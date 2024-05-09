package br.com.manu.model.produto;

public class ItemModelFichaTecnica {
    private long codigo;
    private String descricaoItem;
    private double valorItem;

    public ItemModelFichaTecnica() {

    }

    public ItemModelFichaTecnica(long codigo, String descricaoItem, double valorItem) {
        this.codigo = codigo;
        this.descricaoItem = descricaoItem;
        this.valorItem = valorItem;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }
}
