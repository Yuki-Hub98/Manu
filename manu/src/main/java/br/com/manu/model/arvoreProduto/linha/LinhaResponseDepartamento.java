package br.com.manu.model.arvoreProduto.linha;

public class LinhaResponseDepartamento {
    private String departamento;
    private String linha;

    public LinhaResponseDepartamento() {

    }

    public LinhaResponseDepartamento(String departamento, String linha) {
        this.departamento = departamento;
        this.linha = linha;
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
}
