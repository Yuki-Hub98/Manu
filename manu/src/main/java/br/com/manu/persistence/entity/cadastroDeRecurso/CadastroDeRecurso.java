package br.com.manu.persistence.entity.cadastroDeRecurso;

import org.springframework.data.annotation.Id;

public class CadastroDeRecurso {
    @Id
    private String id;
    private int codigo;
    private String tipoRecurso;
    private String unidadeMedida;
    private String recurso;
    private float valor;
    private double valorMedida;

    public CadastroDeRecurso() {

    }

    public CadastroDeRecurso(String id, int codigo, String tipoRecurso, String unidadeMedida, String recurso, float valor, double valorMedida) {
        this.id = id;
        this.codigo = codigo;
        this.tipoRecurso = tipoRecurso;
        this.unidadeMedida = unidadeMedida;
        this.recurso = recurso;
        this.valor = valor;
        this.valorMedida = valorMedida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public double getValorMedida() {
        return valorMedida;
    }

    public void setValorMedida(double valorMedida) {
        this.valorMedida = valorMedida;
    }
}
