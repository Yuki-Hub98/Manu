package br.com.manu.persistence.entity.etapaDeProducao;

import org.springframework.data.annotation.Id;

public class EtapaDeProducao {
    @Id
    private String id;
    private int codigo;
    private String etapaDeProducao;

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
}
