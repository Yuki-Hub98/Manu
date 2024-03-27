package br.com.manu.model.cadastroDeFuncao;

public class CadastroDeFuncaoRequest {
    private String funcao;
    private String salario;

    public CadastroDeFuncaoRequest() {

    }

    public CadastroDeFuncaoRequest(String funcao, String salario) {
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
