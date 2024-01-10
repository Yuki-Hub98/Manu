package br.com.manu.service.Cor;

import br.com.manu.model.cor.CorRequest;
import br.com.manu.model.cor.CorResponse;

import java.util.List;

public interface CorService {

    CorResponse create(CorRequest request);

    List<CorResponse> getAll();
}
