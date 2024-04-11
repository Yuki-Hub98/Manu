package br.com.manu.model.recursos.grupoDeRecurso;

public class GrupoDeRecursoResponseSimplified {
    private int codigo;
    private String grupoRecurso;
    private int quantidadeRecursos;
    private double valorTotalUnitario;

    public GrupoDeRecursoResponseSimplified() {

    }

    public GrupoDeRecursoResponseSimplified(int codigo, String grupoRecurso, int quantidadeRecursos, double valorTotalUnitario) {
        this.codigo = codigo;
        this.grupoRecurso = grupoRecurso;
        this.quantidadeRecursos = quantidadeRecursos;
        this.valorTotalUnitario = valorTotalUnitario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getGrupoRecurso() {
        return grupoRecurso;
    }

    public void setGrupoRecurso(String grupoRecurso) {
        this.grupoRecurso = grupoRecurso;
    }

    public int getQuantidadeRecursos() {
        return quantidadeRecursos;
    }

    public void setQuantidadeRecursos(int quantidadeRecursos) {
        this.quantidadeRecursos = quantidadeRecursos;
    }

    public double getValorTotalUnitario() {
        return valorTotalUnitario;
    }

    public void setValorTotalUnitario(double valorTotalUnitario) {
        this.valorTotalUnitario = valorTotalUnitario;
    }
}
