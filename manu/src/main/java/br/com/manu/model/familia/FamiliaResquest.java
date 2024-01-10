package br.com.manu.model.familia;

import org.springframework.data.annotation.Id;

public class FamiliaResquest {

    @Id
    private String id;
    private String linha;
    private String descricao;

    public FamiliaResquest() {
    }

    public FamiliaResquest(String id, String linha, String descricao) {
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
