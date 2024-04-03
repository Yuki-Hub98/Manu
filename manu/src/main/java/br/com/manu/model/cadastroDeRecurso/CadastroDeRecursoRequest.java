package br.com.manu.model.cadastroDeRecurso;

public class CadastroDeRecursoRequest {
    private int codigo;
    private String tipoRecurso;
    private String unidadeMedida;
    private String recurso;
    private String valor;
    private String valorMedida;

    public CadastroDeRecursoRequest() {

    }

    public CadastroDeRecursoRequest(int codigo, String tipoRecurso, String unidadeMedida, String recurso, String valor, String valorMedida) {
        this.codigo = codigo;
        this.tipoRecurso = tipoRecurso;
        this.unidadeMedida = unidadeMedida;
        this.recurso = recurso;
        this.valor = valor;
        this.valorMedida = valorMedida;
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

    public String getValorMedida() {
        return valorMedida;
    }

    public void setValorMedida(String valorMedida) {
        this.valorMedida = valorMedida;
    }
}
