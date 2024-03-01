package br.com.manu.model.codigoBarra;

public class CodigoBarraRequest {
    private String fornecedor;
    private int idItem;

    public CodigoBarraRequest() {

    }

    public CodigoBarraRequest(String fornecedor, int idItem) {
        this.fornecedor = fornecedor;
        this.idItem = idItem;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}

