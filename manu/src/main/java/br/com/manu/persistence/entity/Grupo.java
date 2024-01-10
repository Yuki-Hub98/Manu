package br.com.manu.persistence.entity;

import org.springframework.data.annotation.Id;

public class Grupo {
    @Id
    private String id;
    private String familia;
    private String descricao;

    public Grupo() {
    }

    public Grupo(String id, String familia, String descricao) {
        this.id = id;
        this.familia = familia;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
