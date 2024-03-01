package br.com.manu.controller.codigoBarra;

import br.com.manu.model.codigoBarra.CodigoBarraRequest;
import br.com.manu.model.codigoBarra.CodigoBarraResponse;
import br.com.manu.service.codigoBarra.CodigoBarraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/codigoBarras")
public class CodigoBarraController {
    @Autowired
    CodigoBarraService service;

    @PostMapping
    public ResponseEntity<CodigoBarraResponse> createCodigoBarra(@RequestBody CodigoBarraRequest request){
        return ResponseEntity.ok(service.createCodigoBarra(request));
    }
}
