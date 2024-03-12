package br.com.manu.model.produto;

public class ProdutoCstIcmsResponse {
    private String descricao;

    public ProdutoCstIcmsResponse() {

    }

    public ProdutoCstIcmsResponse(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
