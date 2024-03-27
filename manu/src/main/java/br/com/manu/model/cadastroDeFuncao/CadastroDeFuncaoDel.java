package br.com.manu.model.cadastroDeFuncao;

public class CadastroDeFuncaoDel {
    private int codigo;
    private String del;
    public CadastroDeFuncaoDel() {

    }

    public CadastroDeFuncaoDel(int codigo, String del) {
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
