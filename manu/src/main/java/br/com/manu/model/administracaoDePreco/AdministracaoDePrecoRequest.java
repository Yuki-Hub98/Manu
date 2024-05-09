package br.com.manu.model.administracaoDePreco;

public class AdministracaoDePrecoRequest {
    private long codigo;
    private String descricaoItem;
    private double precoAtual;
    private double valorCusto;
    private double margemAtual;
    private double margemProposta;
    private double precoProposto;

    public AdministracaoDePrecoRequest() {

    }
    public AdministracaoDePrecoRequest(long codigo, String descricaoItem, double precoAtual, double valorCusto, double margemAtual, double margemProposta,
                                       double precoProposto) {
        this.codigo = codigo;
        this.descricaoItem = descricaoItem;
        this.precoAtual = precoAtual;
        this.valorCusto = valorCusto;
        this.margemAtual = margemAtual;
        this.margemProposta = margemProposta;
        this.precoProposto = precoProposto;

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
    public double getPrecoAtual() {
        return precoAtual;
    }
    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }
    public double getValorCusto() {
        return valorCusto;
    }
    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }
    public double getMargemAtual() {
        return margemAtual;
    }
    public void setMargemAtual(double margemAtual) {
        this.margemAtual = margemAtual;
    }
    public double getMargemProposta() {
        return margemProposta;
    }
    public void setMargemProposta(double margemProposta) {
        this.margemProposta = margemProposta;
    }
    public double getPrecoProposto() {
        return precoProposto;
    }
    public void setPrecoProposto(double precoProposto) {
        this.precoProposto = precoProposto;
    }
}
