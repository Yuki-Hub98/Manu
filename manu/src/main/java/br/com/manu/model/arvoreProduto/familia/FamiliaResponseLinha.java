package br.com.manu.model.arvoreProduto.familia;

public class FamiliaResponseLinha {
    private String linha;
    private String familia;

    public FamiliaResponseLinha(String linha, String familia) {
        this.linha = linha;
        this.familia = familia;
    }

    public FamiliaResponseLinha() {

    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }
}
