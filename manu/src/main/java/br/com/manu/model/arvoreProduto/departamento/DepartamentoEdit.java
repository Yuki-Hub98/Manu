package br.com.manu.model.arvoreProduto.departamento;

public class DepartamentoEdit {

    private String descricao;
    private String edit;

    public DepartamentoEdit() {

    }

    public DepartamentoEdit(String descricao, String edit) {
        this.descricao = descricao;
        this.edit = edit;
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
