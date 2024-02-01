package br.com.manu.model.arvoreProduto.linha;

public class LinhaEdit {
    private String departamento;
    private String editDepartamento;
    private String descricao;
    private String editDescricao;


    public LinhaEdit() {
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEditDepartamento() {
        return editDepartamento;
    }

    public void setEditDepartamento(String editDepartamento) {
        this.editDepartamento = editDepartamento;
    }

    public String getEditDescricao() {
        return editDescricao;
    }

    public void setEditDescricao(String editDescricao) {
        this.editDescricao = editDescricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
