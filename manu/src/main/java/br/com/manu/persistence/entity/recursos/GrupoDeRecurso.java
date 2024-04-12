package br.com.manu.persistence.entity.recursos;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

public class GrupoDeRecurso {
    @Id
    private String id;
    private int codigo;
    private String grupoRecurso;
    private double valorTotalUnitario;
    private List<RecursoRequest> recursos;
    @CreatedDate
    private Date dataCriacao;
    @LastModifiedDate
    private Date dataModificacao;
    public GrupoDeRecurso() {
    }

    public GrupoDeRecurso(String id, int codigo, String grupoRecurso, double valorTotalUnitario, List<RecursoRequest> recursos) {
        this.id = id;
        this.codigo = codigo;
        this.grupoRecurso = grupoRecurso;
        this.valorTotalUnitario = valorTotalUnitario;
        this.recursos = recursos;
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

    public String getGrupoRecurso() {
        return grupoRecurso;
    }

    public void setGrupoRecurso(String grupoRecurso) {
        this.grupoRecurso = grupoRecurso;
    }

    public double getValorTotalUnitario() {
        return valorTotalUnitario;
    }

    public void setValorTotalUnitario(double valorTotalUnitario) {
        this.valorTotalUnitario = valorTotalUnitario;
    }

    public List<RecursoRequest> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoRequest> recursos) {
        this.recursos = recursos;
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
