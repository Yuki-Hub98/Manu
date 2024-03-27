package br.com.manu.model.arvoreProduto.grupo;

public class GrupoDel {
    private int codigo;
    private String delFamilia;
    private String del;

    public GrupoDel() {

    }

    public GrupoDel(int codigo, String delFamilia, String del) {
        this.codigo = codigo;
        this.delFamilia = delFamilia;
        this.del = del;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDelFamilia() {
        return delFamilia;
    }

    public void setDelFamilia(String delFamilia) {
        this.delFamilia = delFamilia;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
