package br.com.manu.model.fornecedor;

public class FornecedorDel {
    private int codigo;
    private String del;
    private String message;

    public FornecedorDel(int codigo, String del, String message) {
        this.codigo = codigo;
        this.del = del;
        this.message = message;
    }

    public FornecedorDel() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
