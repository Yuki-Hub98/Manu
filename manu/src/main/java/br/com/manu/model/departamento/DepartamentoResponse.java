package br.com.manu.model.departamento;

public class DepartamentoResponse {

    private String descricao;


    public DepartamentoResponse() {

    }

    public DepartamentoResponse(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
