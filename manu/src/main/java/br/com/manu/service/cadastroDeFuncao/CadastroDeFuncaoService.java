package br.com.manu.service.maoDeObra;

import br.com.manu.model.maoDeObra.MaoDeObraDel;
import br.com.manu.model.maoDeObra.MaoDeObraRequest;
import br.com.manu.model.maoDeObra.MaoDeObraResponse;

import java.util.List;

public interface MaoDeObraService {
    MaoDeObraResponse create(MaoDeObraRequest request);
    List<MaoDeObraResponse> getAll();
    MaoDeObraResponse edit(int codigo, MaoDeObraRequest request);
    MaoDeObraDel del(int id);

}
