package br.com.manu.model.recursos.grupoDeRecurso;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;

public class RecursoModel extends RecursoRequest {
    private double valor;
    private double valorUnitario;

    public RecursoModel() {
    }

    public RecursoModel(int codigo, String tipoRecurso, String unidadeMedida, String recurso, double valor, double valorUnitario) {
        super(codigo, tipoRecurso, unidadeMedida, recurso);
        this.valor = valor;
        this.valorUnitario = valorUnitario;
    }

    public RecursoModel(int codigo, String tipoRecurso, String unidadeMedida, String recurso) {
        super(codigo, tipoRecurso, unidadeMedida, recurso);
    }

    public RecursoModel(double valor, double valorUnitario) {
        this.valor = valor;
        this.valorUnitario = valorUnitario;
    }

}
