package br.com.manu.service.arvoreProduto.grupo;

import br.com.manu.model.arvoreProduto.grupo.*;


import java.util.List;

public interface GrupoService  {
    GrupoResponse create(GrupoRequest request);
    List<GrupoResponse> getAll();
    List<GrupoResponse> getDescricao(String request);
    List<GrupoResponseFamilia> getGrupoByFamilia(String request);
    GrupoResponse edit(GrupoEdit request);
    GrupoDel del(String descricao, GrupoRequest request);
}
