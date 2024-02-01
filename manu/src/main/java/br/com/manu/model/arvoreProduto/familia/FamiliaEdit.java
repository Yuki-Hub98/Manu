package br.com.manu.model.arvoreProduto.familia;

public class FamiliaEdit {
    private String linha;
    private String editLinha;
    private String descricao;
    private String editDescricao;

    public FamiliaEdit() {
    }

    public FamiliaEdit(String linha, String editLinha, String descricao, String editDescricao) {
        this.linha = linha;
        this.editLinha = editLinha;
        this.descricao = descricao;
        this.editDescricao = editDescricao;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getEditLinha() {
        return editLinha;
    }

    public void setEditLinha(String editLinha) {
        this.editLinha = editLinha;
    }



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEditDescricao() {
        return editDescricao;
    }

}
