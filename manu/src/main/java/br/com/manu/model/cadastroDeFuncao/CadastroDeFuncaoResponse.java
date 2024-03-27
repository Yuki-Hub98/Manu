package br.com.manu.model.maoDeObra;

public class MaoDeObraResponse {
    private int codigo;
    private String funcao;
    private String salario;
    private String valorMinuto;

    public MaoDeObraResponse() {

    }

    public MaoDeObraResponse(int codigo, String funcao, String salario, String valorMinuto) {
        this.codigo = codigo;
        this.funcao = funcao;
        this.salario = salario;
        this.valorMinuto = valorMinuto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getValorMinuto() {
        return valorMinuto;
    }

    public void setValorMinuto(String valorMinuto) {
        this.valorMinuto = valorMinuto;
    }
}
