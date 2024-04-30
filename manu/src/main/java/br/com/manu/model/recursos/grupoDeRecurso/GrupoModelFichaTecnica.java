package br.com.manu.model.recursos.grupoDeRecurso;

public class GrupoModelFichaTecnica {
    private int codigo;
    private String grupoRecurso;
    private double valorTotalUnitario;
    private String tipo;

    public GrupoModelFichaTecnica() {

    }

    public GrupoModelFichaTecnica(int codigo, String grupoRecurso, double valorTotalUnitario) {
        this.codigo = codigo;
        this.grupoRecurso = grupoRecurso;
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

    public double getValorTotalUnitario() {
        return valorTotalUnitario;
    }

    public void setValorTotalUnitario(double valorTotalUnitario) {
        this.valorTotalUnitario = valorTotalUnitario;
    }
}
