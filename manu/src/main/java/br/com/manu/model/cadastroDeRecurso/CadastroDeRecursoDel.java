package br.com.manu.model.cadastroDeRecurso;

public class CadastroDeRecursoDel {
    private int codigo;
    private String del;
    public CadastroDeRecursoDel() {

    }

    public CadastroDeRecursoDel(int codigo, String del) {
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
