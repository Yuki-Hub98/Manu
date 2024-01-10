package br.com.manu.service.familia;

import br.com.manu.model.familia.FamiliaResponse;
import br.com.manu.model.familia.FamiliaResquest;

import java.util.List;

public interface FamiliaService {
    FamiliaResponse create(FamiliaResquest resquest);
    List<FamiliaResponse> getAll();
}
