package br.com.manu.model.produto;

public class ModeloRe {
    private int codigo;
    private String descricao;

    public ModeloRe() {
    }

    public ModeloRe(int codigo, String descricao) {
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
