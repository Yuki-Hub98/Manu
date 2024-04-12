package br.com.manu.persistence.entity.etapaDeProducao;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class EtapaDeProducao {
    @Id
    private String id;
    private int codigo;
    private String etapaDeProducao;
    @CreatedDate
    private Date dataCriacao;
    @LastModifiedDate
    private Date dataModificacao;

    public EtapaDeProducao() {
    }

    public EtapaDeProducao(String id, int codigo, String etapaDeProducao) {
        this.id = id;
        this.codigo = codigo;
        this.etapaDeProducao = etapaDeProducao;
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

    public String getEtapaDeProducao() {
        return etapaDeProducao;
    }

    public void setEtapaDeProducao(String etapaDeProducao) {
        this.etapaDeProducao = etapaDeProducao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
