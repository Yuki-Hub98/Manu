package br.com.manu.model.arvoreProduto.familia;

public class FamiliaResponseLinha {
    private int codigo;
    private String linha;
    private String familia;

    public FamiliaResponseLinha(int codigo, String linha, String familia) {
        this.codigo = codigo;
        this.linha = linha;
        this.familia = familia;
    }

    public FamiliaResponseLinha() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
