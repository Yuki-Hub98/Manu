package br.com.manu.model.etapaDeProducao;

public class EtapaDeProducaoFichaTecnica {
    private int codigo;
    private String etapaDeProducao;

    public EtapaDeProducaoFichaTecnica() {

    }

    public EtapaDeProducaoFichaTecnica(int codigo, String etapaDeProducao) {
        this.codigo = codigo;
        this.etapaDeProducao = etapaDeProducao;
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
}
