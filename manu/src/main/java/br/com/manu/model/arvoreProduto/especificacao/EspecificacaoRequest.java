package br.com.manu.model.arvoreProduto.especificacao;

public class EspecificacaoRequest {
    private int codigo;
    private String descricao;

    public EspecificacaoRequest() {

    }
    public EspecificacaoRequest(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
