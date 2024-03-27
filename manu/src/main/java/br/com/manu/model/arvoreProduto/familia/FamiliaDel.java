package br.com.manu.model.arvoreProduto.familia;

public class FamiliaDel {
    private int codigo;
    private String delLinha;
    private String del;

    public FamiliaDel() {
    }

    public FamiliaDel(int codigo, String delLinha, String del) {
        this.codigo = codigo;
        this.delLinha = delLinha;
        this.del = del;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDelLinha() {
        return delLinha;
    }

    public void setDelLinha(String delLinha) {
        this.delLinha = delLinha;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
