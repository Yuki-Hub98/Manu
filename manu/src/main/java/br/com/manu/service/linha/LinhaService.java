package br.com.manu.service.linha;

import br.com.manu.model.linha.LinhaRequest;
import br.com.manu.model.linha.LinhaResponse;

import java.util.List;

public interface LinhaService {
    LinhaResponse create(LinhaRequest request);
    List<LinhaResponse> getAll();
}
