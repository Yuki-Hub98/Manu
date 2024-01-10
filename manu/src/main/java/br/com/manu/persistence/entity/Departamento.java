package br.com.manu.persistence.entity;

import org.springframework.data.annotation.Id;

public class Departamento {
    @Id
    private String id;
    private String descricao;

    public Departamento(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Departamento() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
