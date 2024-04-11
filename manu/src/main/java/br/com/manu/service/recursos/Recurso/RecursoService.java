package br.com.manu.service.recursos.Recurso;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoDel;
import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;
import br.com.manu.model.recursos.cadastroDeRecurso.RecursoResponse;
import java.util.List;

public interface RecursoService {
    RecursoResponse create(RecursoRequest request);
    List<RecursoResponse> getAll();
    List<RecursoResponse> searchRecurso(String recurso);
    RecursoResponse edit(int codigo, RecursoRequest request);
    RecursoDel del(int id);

}
