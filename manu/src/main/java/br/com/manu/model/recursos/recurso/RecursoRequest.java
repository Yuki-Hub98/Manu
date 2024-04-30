package br.com.manu.model.recursos.recurso;

public class RecursoRequest {
    private int codigo;
    private String tipoRecurso;
    private String unidadeMedida;
    private String recurso;
    private String valor;
    private String valorUnitario;

    public RecursoRequest() {

    }

    public RecursoRequest(int codigo, String tipoRecurso, String unidadeMedida, String recurso, String valor, String valorUnitario) {
        this.codigo = codigo;
        this.tipoRecurso = tipoRecurso;
        this.unidadeMedida = unidadeMedida;
        this.recurso = recurso;
        this.valor = valor;
        this.valorUnitario = valorUnitario;
    }

    public RecursoRequest(int codigo, String tipoRecurso, String unidadeMedida, String recurso) {
        this.codigo = codigo;
        this.tipoRecurso = tipoRecurso;
        this.unidadeMedida = unidadeMedida;
        this.recurso = recurso;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
