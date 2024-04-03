package br.com.manu.model.cadastroDeRecurso;

public class CadastroDeRecursoResponse extends CadastroDeRecursoRequest{
    public CadastroDeRecursoResponse() {
    }
    public CadastroDeRecursoResponse(int codigo, String tipoRecurso, String unidadeMedida,
                                     String recurso, String valor, String valorMinuto) {
        super(codigo, tipoRecurso, unidadeMedida, recurso, valor, valorMinuto);
    }
}
