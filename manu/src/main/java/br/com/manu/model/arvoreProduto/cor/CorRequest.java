package br.com.manu.model.arvoreProduto.cor;

public class CorRequest {

    private String descricao;

    public CorRequest() {
    }

    public CorRequest(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
