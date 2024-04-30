package br.com.manu.model.fichaTecnica;

public class FichaTecnicaSummary {
    private int codigo;
    private String fichaTecnica;
    private double valorTotalRecurso;
    private double valorTotalItem;
    private int tempoEstimadoEmMinutos;
    private int quantidadeEtapas;

    public FichaTecnicaSummary() {

    }

    public FichaTecnicaSummary(int codigo, String fichaTecnica, double valorTotalRecurso, double valorTotalItem, int tempoEstimadoEmMinutos, int quantidadeEtapas) {
        this.codigo = codigo;
        this.fichaTecnica = fichaTecnica;
        this.valorTotalRecurso = valorTotalRecurso;
        this.valorTotalItem = valorTotalItem;
        this.tempoEstimadoEmMinutos = tempoEstimadoEmMinutos;
        this.quantidadeEtapas = quantidadeEtapas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(String fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public double getValorTotalRecurso() {
        return valorTotalRecurso;
    }

    public void setValorTotalRecurso(double valorTotalRecurso) {
        this.valorTotalRecurso = valorTotalRecurso;
    }

    public double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

    public int getTempoEstimadoEmMinutos() {
        return tempoEstimadoEmMinutos;
    }

    public void setTempoEstimadoEmMinutos(int tempoEstimadoEmMinutos) {
        this.tempoEstimadoEmMinutos = tempoEstimadoEmMinutos;
    }

    public int getQuantidadeEtapas() {
        return quantidadeEtapas;
    }

    public void setQuantidadeEtapas(int quantidadeEtapas) {
        this.quantidadeEtapas = quantidadeEtapas;
    }
}
