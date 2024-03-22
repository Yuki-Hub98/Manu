package br.com.manu.persistence.entity.produtos.modelo;

import org.springframework.data.annotation.Id;

public class Modelo {
    @Id
    private String id;
    private String descricao;

    public Modelo() {
    }

    public Modelo(String descricao) {
        this.descricao = descricao;
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
