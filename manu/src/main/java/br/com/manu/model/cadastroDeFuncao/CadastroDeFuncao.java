package br.com.manu.model.cadastroDeFuncao;

public class CadastroDeFuncao {
    private int id;
    private String del;
    public CadastroDeFuncao() {

    }
    public CadastroDeFuncao(int id, String del) {
        this.id = id;
        this.del = del;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
