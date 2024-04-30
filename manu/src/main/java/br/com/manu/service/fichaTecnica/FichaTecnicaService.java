package br.com.manu.service.fichaTecnica;

import br.com.manu.model.fichaTecnica.FichaTecnicaDel;
import br.com.manu.model.fichaTecnica.FichaTecnicaModalItemRecursoEtapa;
import br.com.manu.model.fichaTecnica.FichaTecnicaRequest;
import br.com.manu.model.fichaTecnica.FichaTecnicaResponse;
import br.com.manu.persistence.entity.recursos.GrupoDeRecurso;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface FichaTecnicaService {
    FichaTecnicaResponse create(FichaTecnicaRequest request);
    List<FichaTecnicaResponse> getAll();
    FichaTecnicaResponse edit(FichaTecnicaRequest request, int codigo);
    List<FichaTecnicaResponse> getParams(String fichaTecnica);
    FichaTecnicaModalItemRecursoEtapa getModalItemRecursoEtapa();
    FichaTecnicaDel del(FichaTecnicaRequest request, int codigo);
    void updateFichaTecnicaByGrupoRecusos(Query queryGrupoRecursos);
}
