package br.com.manu.service.arvoreProduto.familia;

import br.com.manu.model.arvoreProduto.familia.FamiliaDel;
import br.com.manu.model.arvoreProduto.familia.FamiliaEdit;
import br.com.manu.model.arvoreProduto.familia.FamiliaResponse;
import br.com.manu.model.arvoreProduto.familia.FamiliaResquest;

import java.util.List;

public interface FamiliaService {
    FamiliaResponse create(FamiliaResquest resquest);
    List<FamiliaResponse> getAll();
    List<FamiliaResponse> getDescricao(String request);
    FamiliaResponse edit(FamiliaEdit request);
    FamiliaDel del(String descricao, FamiliaResquest request);
}
