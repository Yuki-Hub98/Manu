package br.com.manu.model.arvoreProduto.linha;

public class LinhaResponseDepartamento {
    private int codigo;
    private String departamento;
    private String linha;

    public LinhaResponseDepartamento() {

    }

    public LinhaResponseDepartamento(int codigo, String departamento, String linha) {
        this.codigo = codigo;
        this.departamento = departamento;
        this.linha = linha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
