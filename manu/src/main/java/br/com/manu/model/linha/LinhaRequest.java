package br.com.manu.model.linha;

public class LinhaRequest {
    private String departamento;
    private String descricao;

    public LinhaRequest() {

    }
    public LinhaRequest(String departamento, String descricao) {
        this.departamento = departamento;
        this.descricao = descricao;
    }


    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
