package br.com.manu.persistence.entity.tipoProduto;

import org.springframework.data.annotation.Id;

public class TipoProduto {
    @Id
    private String id;
    private String ativoMobiliario;
    private String itemVenda;
    private String materiaPrima;
    private String usoConsumo;

    public TipoProduto() {
    }

    public TipoProduto(String id, String ativoMobiliario, String itemVenda, String materiaPrima, String usoConsumo) {
        this.id = id;
        this.ativoMobiliario = ativoMobiliario;
        this.itemVenda = itemVenda;
        this.materiaPrima = materiaPrima;
        this.usoConsumo = usoConsumo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtivoMobiliario() {
        return ativoMobiliario;
    }

    public void setAtivoMobiliario(String ativoMobiliario) {
        this.ativoMobiliario = ativoMobiliario;
    }

    public String getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(String itemVenda) {
        this.itemVenda = itemVenda;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(String materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public String getUsoConsumo() {
        return usoConsumo;
    }

    public void setUsoConsumo(String usoConsumo) {
        this.usoConsumo = usoConsumo;
    }
}
