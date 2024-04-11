package br.com.manu.persistence.entity.recursos;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;
import org.springframework.data.annotation.Id;

import java.util.List;

public class GrupoDeRecurso {
    @Id
    private String id;
    private int codigo;
    private String grupoRecurso;
    private double valorTotalUnitario;
    private List<RecursoRequest> recursos;
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
}
