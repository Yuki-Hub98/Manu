package br.com.manu.model.arvoreProduto.grupo;

public class GrupoResponseFamilia {
    private String familia;
    private String grupo;

    public GrupoResponseFamilia() {

    }

    public GrupoResponseFamilia(String familia, String grupo) {
        this.familia = familia;
        this.grupo = grupo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
