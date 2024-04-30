package br.com.manu.model.fichaTecnica;

public class ModelItem {
    private int codigo;
    private String descricaoItem;
    private int quantidade;
    private float valorItem;
    private String tipo;
    private double valorTotalItem;
    public ModelItem() {

    }

    public ModelItem(int codigo, String descricaoItem, int quantidade, float valorItem, String tipo, double valorTotalItem) {
        this.codigo = codigo;
        this.descricaoItem = descricaoItem;
        this.quantidade = quantidade;
        this.valorItem = valorItem;
        this.tipo = tipo;
        this.valorTotalItem = valorTotalItem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorItem() {
        return valorItem;
    }

    public void setValorItem(float valorItem) {
        this.valorItem = valorItem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }
}
