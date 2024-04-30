package br.com.manu.model.fichaTecnica;

import java.util.List;

public class FichaTecnicaRequest {
    private int codigo;
    private String fichaTecnica;
    private List<ModelEtapa> etapas;

    public FichaTecnicaRequest() {

    }

    public FichaTecnicaRequest(int codigo, String fichaTecnica, List<ModelEtapa> etapas) {
        this.codigo = codigo;
        this.fichaTecnica = fichaTecnica;
        this.etapas = etapas;
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

    public List<ModelEtapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<ModelEtapa> etapas) {
        this.etapas = etapas;
    }
}
