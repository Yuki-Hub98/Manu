package br.com.manu.model.unidadeMedida;

public class UnidadeMedidaResponse {
    private String status;

    public UnidadeMedidaResponse() {
    }
    public UnidadeMedidaResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
