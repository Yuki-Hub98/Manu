package br.com.manu.model.produto;

import org.springframework.data.annotation.Id;

public class ProdutoNcm {
    String Codigo;
    String Descricao;

    public ProdutoNcm() {

    }

    public ProdutoNcm(String codigo, String descricao) {
        Codigo = codigo;
        Descricao = descricao;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
