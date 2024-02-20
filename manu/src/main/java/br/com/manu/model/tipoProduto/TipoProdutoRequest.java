package br.com.manu.model.tipoProduto;

public class TipoProdutoRequest {
    private String ativoMobiliario;
    private String itemVenda;
    private String materiaPrima;
    private String usoConsumo;

    public TipoProdutoRequest() {

    }
    public TipoProdutoRequest(String ativoMobiliario, String itemVenda, String materiaPrima, String usoConsumo) {
        this.ativoMobiliario = ativoMobiliario;
        this.itemVenda = itemVenda;
        this.materiaPrima = materiaPrima;
        this.usoConsumo = usoConsumo;
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
