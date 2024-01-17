package br.com.manu.model.arvoreProduto.grupo;

import org.springframework.data.annotation.Id;

public class GrupoResponse {

    private String familia;
    private String descricao;

    public GrupoResponse() {

    }

    public GrupoResponse( String familia, String descricao) {

        this.familia = familia;
        this.descricao = descricao;
    }

    public String getFamilia() {
        return familia;
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
