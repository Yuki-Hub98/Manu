package br.com.manu.model.recursos.cadastroDeRecurso;

public class RecursoResponse extends RecursoRequest {
    public RecursoResponse() {
    }

    public RecursoResponse(int codigo, String tipoRecurso, String unidadeMedida, String recurso, String valor, String valorUnitario) {
        super(codigo, tipoRecurso, unidadeMedida, recurso, valor, valorUnitario);
    }
}
