package br.com.manu.model.arvoreProduto.familia;

import org.springframework.data.annotation.Id;

public class FamiliaResponse {
    private int codigo;
    private String linha;
    private String descricao;

    public FamiliaResponse() {

    }

    public FamiliaResponse(int codigo, String linha, String descricao) {
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
