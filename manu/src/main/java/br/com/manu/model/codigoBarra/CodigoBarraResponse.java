package br.com.manu.model.codigoBarra;

public class CodigoBarraResponse {
    private int lastId;
    private String codBarra;


    public CodigoBarraResponse(String codBarra, int lastId) {
        this.codBarra = codBarra;
        this.lastId = lastId;
    }

    public CodigoBarraResponse() {
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }
}
