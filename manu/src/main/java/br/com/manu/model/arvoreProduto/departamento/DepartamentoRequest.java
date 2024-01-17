package br.com.manu.model.arvoreProduto.departamento;

public class DepartamentoRequest {

    private String descricao;

    public DepartamentoRequest() {

    }

    public DepartamentoRequest(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
