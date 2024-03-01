package br.com.manu.service.codigoBarra;

import br.com.manu.model.codigoBarra.CodigoBarraRequest;
import br.com.manu.model.codigoBarra.CodigoBarraResponse;

public interface CodigoBarraService {
    CodigoBarraResponse createCodigoBarra(CodigoBarraRequest request);
}
