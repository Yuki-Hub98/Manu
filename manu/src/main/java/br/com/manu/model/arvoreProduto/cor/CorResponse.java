package br.com.manu.model.arvoreProduto.cor;

public class CorResponse {

    private int codigo;
    private String descricao;

    public CorResponse() {

    }

    public CorResponse(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
