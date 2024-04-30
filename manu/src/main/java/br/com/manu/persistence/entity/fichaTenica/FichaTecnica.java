package br.com.manu.persistence.entity.fichaTenica;

import br.com.manu.model.fichaTecnica.ModelEtapa;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

public class FichaTecnica {
    @Id
    private String id;
    private int codigo;
    private String fichaTecnica;
    private double valorTotalRecursos;
    private double valorTotalItens;
    private int tempoEstimadoEmMinutos;
    private List<ModelEtapa> etapas;
    @CreatedDate
    private Date dataCriacao;
    @LastModifiedDate
    private Date dataModificacao;

    public FichaTecnica() {
    }

    public FichaTecnica(String id, int codigo, String fichaTecnica, double valorTotalRecursos, double valorTotalItens, int tempoEstimadoEmMinutos,
                        List<ModelEtapa> etapas, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.codigo = codigo;
        this.fichaTecnica = fichaTecnica;
        this.valorTotalRecursos = valorTotalRecursos;
        this.valorTotalItens = valorTotalItens;
        this.tempoEstimadoEmMinutos = tempoEstimadoEmMinutos;
        this.etapas = etapas;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
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


    public String getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(String fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public double getValorTotalRecursos() {
        return valorTotalRecursos;
    }

    public void setValorTotalRecursos(double valorTotalRecursos) {
        this.valorTotalRecursos = valorTotalRecursos;
    }

    public double getValorTotalItens() {
        return valorTotalItens;
    }

    public void setValorTotalItens(double valorTotalItens) {
        this.valorTotalItens = valorTotalItens;
    }

    public int getTempoEstimadoEmMinutos() {
        return tempoEstimadoEmMinutos;
    }

    public void setTempoEstimadoEmMinutos(int tempoEstimadoEmMinutos) {
        this.tempoEstimadoEmMinutos = tempoEstimadoEmMinutos;
    }

    public List<ModelEtapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<ModelEtapa> etapas) {
        this.etapas = etapas;
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
