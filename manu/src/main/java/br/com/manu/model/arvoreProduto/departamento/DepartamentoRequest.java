package br.com.manu.model.arvoreProduto.departamento;

public class DepartamentoRequest {
    private int codigo;
    private String descricao;

    public DepartamentoRequest() {

    }

    public DepartamentoRequest(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
