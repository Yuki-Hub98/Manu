package br.com.manu.model.maoDeObra;

public class MaoDeObraRequest {
    private String funcao;
    private String salario;

    public MaoDeObraRequest() {

    }

    public MaoDeObraRequest(String funcao, String salario) {
        this.funcao = funcao;
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public String getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
}
