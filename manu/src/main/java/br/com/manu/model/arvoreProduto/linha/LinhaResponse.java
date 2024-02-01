package br.com.manu.model.arvoreProduto.linha;

import br.com.manu.persistence.entity.arvoreProduto.Departamento;

public class LinhaResponse {
    private String departamento;
    private String descricao;

    public LinhaResponse() {

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
