package br.com.manu.persistence.entity.maoDeObra;

import org.springframework.data.annotation.Id;

public class MaoDeObra {
    @Id
    private String id;
    private int codigo;
    private String funcao;
    private float salario;
    private double valorMinuto;

    public MaoDeObra() {

    }

    public MaoDeObra(String id, int codigo, String funcao, float salario, double valorMinuto) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public double getValorMinuto() {
        return valorMinuto;
    }

    public void setValorMinuto(double valorMinuto) {
        this.valorMinuto = valorMinuto;
    }

}
