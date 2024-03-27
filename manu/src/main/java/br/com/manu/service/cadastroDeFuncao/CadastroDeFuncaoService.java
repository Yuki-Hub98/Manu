package br.com.manu.service.cadastroDeFuncao;

import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoDel;
import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoRequest;
import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoResponse;
import java.util.List;

public interface CadastroDeFuncaoService {
    CadastroDeFuncaoResponse create(CadastroDeFuncaoRequest request);
    List<CadastroDeFuncaoResponse> getAll();
    CadastroDeFuncaoResponse edit(int codigo, CadastroDeFuncaoRequest request);
    CadastroDeFuncaoDel del(int id);

}
