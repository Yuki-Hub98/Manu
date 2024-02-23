package br.com.manu.model.produto;

public class ProdutoDel {
    private String del;
    private String message;

    public ProdutoDel() {
    }
    public ProdutoDel(String del, String message) {
        this.del = del;
        this.message = message;
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
