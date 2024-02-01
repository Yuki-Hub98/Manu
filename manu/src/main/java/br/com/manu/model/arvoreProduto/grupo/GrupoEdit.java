package br.com.manu.model.arvoreProduto.grupo;

public class GrupoEdit {
    private String familia;
    private String editFamilia;
    private String descricao;
    private String editDescricao;


    public GrupoEdit() {

    }

    public String getFamilia() {
        return familia;
    }

    public String getEditFamilia() {
        return editFamilia;
    }

    public void setEditFamilia(String editFamilia) {
        this.editFamilia = editFamilia;
    }

    public String getEditDescricao() {
        return editDescricao;
    }

    public void setEditDescricao(String editDescricao) {
        this.editDescricao = editDescricao;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
