package br.com.manu.model.linha;

public class LinhaResponse {
    private String departamento;
    private String descricao;

    public LinhaResponse() {

    }

    public LinhaResponse(String departamento, String descricao) {
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
