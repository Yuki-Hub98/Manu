package br.com.manu.service.recursos.grupoDeRecurso;

import br.com.manu.model.recursos.grupoDeRecurso.*;
import br.com.manu.persistence.entity.recursos.Recurso;

import java.util.List;

public interface GrupoDeRecursoService {
    GrupoDeRecursoResponse create (GrupoDeRecursoRequestSave request);
    List<GrupoDeRecursoResponse> getAll ();
    List<GrupoDeRecursoResponse> searchGrupoRecurso(String grupoRecurso);
    GrupoDeRecursoResponse edit(int id, GrupoDeRecursoRequestSave request);
    GrupoDeRecursoDel dell(int id, GrupoDeRecursoResponseSimplified request);
    void updateGrupoRecursoByRecurso(Recurso recurso);
}
