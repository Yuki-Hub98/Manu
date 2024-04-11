package br.com.manu.model.recursos.grupoDeRecurso;


import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;

import java.util.List;

public class GrupoDeRecursoRequestSave {
    private int codigo;
    private String grupoRecurso;
    private List<RecursoRequest> recursos;

    public GrupoDeRecursoRequestSave() {

    }

    public GrupoDeRecursoRequestSave(int codigo, String grupoRecurso, List<RecursoRequest> recursos) {
        this.codigo = codigo;
        this.grupoRecurso = grupoRecurso;
        this.recursos = recursos;
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

    public List<RecursoRequest> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoRequest> recursos) {
        this.recursos = recursos;
    }
}
