package br.com.manu.model.arvoreProduto.especificacao;

public class EspecificacaoRequest {
    private String descricao;

    public EspecificacaoRequest() {

    }
    public EspecificacaoRequest(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
