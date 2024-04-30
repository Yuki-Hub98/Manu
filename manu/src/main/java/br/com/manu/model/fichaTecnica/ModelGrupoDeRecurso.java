package br.com.manu.model.fichaTecnica;

public class ModelGrupoDeRecurso {
    private int codigo;
    private String grupoRecurso;
    private int quantidade;
    private double  valorTotalUnitario;
    private String tipo;
    private double valorTotalRecurso;

    public ModelGrupoDeRecurso() {

    }

    public ModelGrupoDeRecurso(int codigo, String grupoRecurso, int quantidade, double valorTotalUnitario, String tipo, double valorTotalRecurso) {
        this.codigo = codigo;
        this.grupoRecurso = grupoRecurso;
        this.quantidade = quantidade;
        this.valorTotalUnitario = valorTotalUnitario;
        this.tipo = tipo;
        this.valorTotalRecurso = valorTotalRecurso;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotalUnitario() {
        return valorTotalUnitario;
    }

    public void setValorTotalUnitario(double valorTotalUnitario) {
        this.valorTotalUnitario = valorTotalUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorTotalRecurso() {
        return valorTotalRecurso;
    }

    public void setValorTotalRecurso(double valorTotalRecurso) {
        this.valorTotalRecurso = valorTotalRecurso;
    }
}
