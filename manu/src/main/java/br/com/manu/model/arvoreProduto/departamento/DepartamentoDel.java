package br.com.manu.model.arvoreProduto.departamento;

public class DepartamentoDel {
    private int codigo;
    private String del;
    public DepartamentoDel() {
    }

    public DepartamentoDel(int codigo, String del) {
        this.codigo = codigo;
        this.del = del;
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
}
