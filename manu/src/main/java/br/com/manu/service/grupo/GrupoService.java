package br.com.manu.service.grupo;

import br.com.manu.model.grupo.GrupoRequest;
import br.com.manu.model.grupo.GrupoResponse;


import java.util.List;

public interface GrupoService  {
    GrupoResponse create(GrupoRequest request);
    List<GrupoResponse> getAll();
}
