package br.com.manu.persistence.entity.arvoreProduto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "familia")
public class Familia {
    @Id
    private String id;
    private int codigo;
    private String linha;
    private String descricao;

    public Familia() {

    }

    public Familia(String id, int codigo, String linha, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.linha = linha;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
