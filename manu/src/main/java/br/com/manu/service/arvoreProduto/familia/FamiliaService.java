package br.com.manu.service.arvoreProduto.familia;

import br.com.manu.model.arvoreProduto.familia.*;

import java.util.List;

public interface FamiliaService {
    FamiliaResponse create(FamiliaResquest resquest);
    List<FamiliaResponse> getAll();
    List<FamiliaResponse> getDescricao(String request);
    List<FamiliaResponseLinha> getFamiliaByLinha(String request);
    FamiliaResponse edit(int codigo, FamiliaResquest request);
    FamiliaDel del(int codigo, FamiliaResquest request);
}
