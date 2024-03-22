package br.com.manu.model.produto;

public class ModeloEdit {
    private String descricao;
    private String edit;

    public ModeloEdit(String descricao, String edit) {
        this.descricao = descricao;
        this.edit = edit;
    }

    public ModeloEdit() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }
}
