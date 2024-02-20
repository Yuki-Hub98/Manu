package br.com.manu.controller.unidadeMedida;

import br.com.manu.model.unidadeMedida.UnidadeMedidaRequest;
import br.com.manu.model.unidadeMedida.UnidadeMedidaResponse;
import br.com.manu.service.unidadeMedida.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/unidadeMedida")
public class UnidadeMedidaController {
    @Autowired
    UnidadeMedidaService service;
    @PostMapping
    public ResponseEntity<String> create(@RequestBody UnidadeMedidaRequest unidade){
        return ResponseEntity.ok(service.create(unidade));
    }
}
