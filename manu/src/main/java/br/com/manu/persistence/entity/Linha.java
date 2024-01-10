package br.com.manu.persistence.entity;

import org.springframework.data.annotation.Id;

public class Linha {
    @Id
    private String id;
    private String departamento;
    private String descricao;

    public Linha(String id, String departamento, String descricao) {
        this.id = id;
        this.departamento = departamento;
        this.descricao = descricao;
    }

    public Linha() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
