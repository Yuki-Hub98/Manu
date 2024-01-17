package br.com.manu.service.arvoreProduto.familia;

import br.com.manu.model.arvoreProduto.familia.FamiliaResponse;
import br.com.manu.model.arvoreProduto.familia.FamiliaResquest;

import java.util.List;

public interface FamiliaService {
    FamiliaResponse create(FamiliaResquest resquest);
    List<FamiliaResponse> getAll();
}
