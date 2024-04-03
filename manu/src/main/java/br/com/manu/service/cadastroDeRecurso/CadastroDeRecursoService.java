package br.com.manu.service.cadastroDeRecurso;

import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoDel;
import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoRequest;
import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoResponse;
import java.util.List;

public interface CadastroDeRecursoService {
    CadastroDeRecursoResponse create(CadastroDeRecursoRequest request);
    List<CadastroDeRecursoResponse> getAll();
    CadastroDeRecursoResponse edit(int codigo, CadastroDeRecursoRequest request);
    CadastroDeRecursoDel del(int id);

}
