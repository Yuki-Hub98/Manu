package br.com.manu.model.familia;

import org.springframework.data.annotation.Id;

public class FamiliaResponse {
    @Id
    private String id;
    private String linha;
    private String descricao;

    public FamiliaResponse() {

    }

    public FamiliaResponse(String id, String linha, String descricao) {
        this.id = id;
        this.linha = linha;
        this.descricao = descricao;
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
