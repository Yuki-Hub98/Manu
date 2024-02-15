package br.com.manu.model.fornecedor;

public class FornecedorDel {
    private String del;
    private String message;

    public FornecedorDel(String del, String message) {
        this.del = del;
        this.message = message;
    }

    public FornecedorDel() {
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
