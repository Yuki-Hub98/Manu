package br.com.manu.model.fichaTecnica;

import java.util.List;

public class ModelEtapa {
    private int codigo;
    private String etapaDeProducao;
    private List<ModelGrupoDeRecurso> etapaDeProducaoRecursos;
    private List<ModelItem> etapaDeProducaoItems;

    public ModelEtapa() {
    }

    public ModelEtapa(int codigo, String etapaDeProducao, List<ModelGrupoDeRecurso> etapaDeProducaoRecursos, List<ModelItem> etapaDeProducaoItems) {
        this.codigo = codigo;
        this.etapaDeProducao = etapaDeProducao;
        this.etapaDeProducaoRecursos = etapaDeProducaoRecursos;
        this.etapaDeProducaoItems = etapaDeProducaoItems;
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

    public List<ModelGrupoDeRecurso> getEtapaDeProducaoRecursos() {
        return etapaDeProducaoRecursos;
    }

    public void setEtapaDeProducaoRecursos(List<ModelGrupoDeRecurso> etapaDeProducaoRecursos) {
        this.etapaDeProducaoRecursos = etapaDeProducaoRecursos;
    }

    public List<ModelItem> getEtapaDeProducaoItems() {
        return etapaDeProducaoItems;
    }

    public void setEtapaDeProducaoItems(List<ModelItem> etapaDeProducaoItems) {
        this.etapaDeProducaoItems = etapaDeProducaoItems;
    }
}
