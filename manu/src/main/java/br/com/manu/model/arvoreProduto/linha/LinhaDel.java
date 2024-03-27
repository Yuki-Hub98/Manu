package br.com.manu.model.arvoreProduto.linha;

public class LinhaDel {
    private int codigo;
    private String depDel;
    private String del;

    public LinhaDel() {
    }

    public LinhaDel(int codigo, String depDel, String del) {
        this.codigo = codigo;
        this.depDel = depDel;
        this.del = del;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDepDel() {
        return depDel;
    }

    public void setDepDel(String depDel) {
        this.depDel = depDel;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
