package br.com.manu.persistence.entity.arvoreProduto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "familia")
public class Familia {
    @Id
    private String id;
    private String linha;
    private String descricao;

    public Familia() {

    }

    public Familia(String id, String linha, String descricao) {
        this.id = id;
        this.linha = linha;
        this.descricao = descricao;
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
