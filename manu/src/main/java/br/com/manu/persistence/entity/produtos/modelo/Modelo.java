package br.com.manu.persistence.entity.produtos.modelo;

import org.springframework.data.annotation.Id;

public class Modelo {
    @Id
    private String id;
    private int codigo;
    private String descricao;

    public Modelo() {
    }

    public Modelo(String id, int codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
