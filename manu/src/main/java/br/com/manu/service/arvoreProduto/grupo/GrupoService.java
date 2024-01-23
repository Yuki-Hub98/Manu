package br.com.manu.service.arvoreProduto.grupo;

import br.com.manu.model.arvoreProduto.grupo.GrupoRequest;
import br.com.manu.model.arvoreProduto.grupo.GrupoResponse;


import java.util.List;

public interface GrupoService  {
    GrupoResponse create(GrupoRequest request);
    List<GrupoResponse> getAll();
    List<GrupoResponse> getDescricao(String request);
}
