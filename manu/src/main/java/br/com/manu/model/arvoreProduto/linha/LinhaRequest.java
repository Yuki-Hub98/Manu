package br.com.manu.model.arvoreProduto.linha;

import br.com.manu.persistence.entity.arvoreProduto.Departamento;

public class LinhaRequest {
    private int codigo;
    private String departamento;
    private String descricao;

    public LinhaRequest() {

    }

    public LinhaRequest(int codigo, String departamento, String descricao) {
        this.codigo = codigo;
        this.departamento = departamento;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
