package br.com.manu.model.arvoreProduto.grupo;

public class GrupoResponseFamilia {
    private int codigo;
    private String familia;
    private String grupo;

    public GrupoResponseFamilia() {

    }

    public GrupoResponseFamilia(int codigo, String familia, String grupo) {
        this.codigo = codigo;
        this.familia = familia;
        this.grupo = grupo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
