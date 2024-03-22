package br.com.manu.model.produto;

public class Modelo {
    private String descricao;

    public Modelo(String descricao) {
        this.descricao = descricao;
    }
    public Modelo() {

    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
