package br.com.manu.persistence.entity.produtos.csticms;

import org.springframework.data.annotation.Id;

public class CstIcms {
    @Id
    private String _id;
    private String origem;
    private String codigo;
    private String descricao;

    public CstIcms() {

    }

    public CstIcms(String _id, String origem, String codigo, String descricao) {
        this._id = _id;
        this.origem = origem;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
