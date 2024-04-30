package br.com.manu.service.recursos.recurso;

import br.com.manu.model.recursos.recurso.RecursoDel;
import br.com.manu.model.recursos.recurso.RecursoRequest;
import br.com.manu.model.recursos.recurso.RecursoResponse;
import java.util.List;

public interface RecursoService {
    RecursoResponse create(RecursoRequest request);
    List<RecursoResponse> getAll();
    List<RecursoResponse> searchRecurso(String recurso);
    RecursoResponse edit(int codigo, RecursoRequest request);
    RecursoDel del(RecursoRequest request, int id);

}
