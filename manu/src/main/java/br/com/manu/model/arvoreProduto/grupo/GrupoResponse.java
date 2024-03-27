package br.com.manu.model.arvoreProduto.grupo;

import org.springframework.data.annotation.Id;

public class GrupoResponse {
    private int codigo;
    private String familia;
    private String descricao;

    public GrupoResponse() {

    }

    public GrupoResponse(int codigo, String familia, String descricao) {
        this.codigo = codigo;
        this.familia = familia;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
