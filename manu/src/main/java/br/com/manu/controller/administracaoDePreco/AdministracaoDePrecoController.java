package br.com.manu.controller.administracaoDePreco;

import br.com.manu.model.administracaoDePreco.AdministracaoDePrecoRequest;
import br.com.manu.model.administracaoDePreco.AdministracaoDePrecoResponse;
import br.com.manu.service.administracaoDePreco.AdministracaoDePrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administracao-de-preco")
public class AdministracaoDePrecoController {
    @Autowired
    AdministracaoDePrecoService service;

    @PutMapping
    public ResponseEntity <AdministracaoDePrecoResponse> updateAdministracaoDePreco(@RequestBody List<AdministracaoDePrecoRequest> request){
        return ResponseEntity.ok(service.updateAdministracaoDePreco(request));
    }

    @GetMapping
    public ResponseEntity<List<AdministracaoDePrecoResponse>> getParams(@RequestParam("codigo") String codigo,
                                                                        @RequestParam ("descricaoItem") String descricaoItem,
                                                                        @RequestParam ("codBarra") String codBarra,
                                                                        @RequestParam ("departamento") String departamento,
                                                                        @RequestParam ("linha") String linha,
                                                                        @RequestParam ("modelo") String modelo){

        return ResponseEntity.ok(service.getParams(codigo, descricaoItem, codBarra, departamento, linha, modelo));
    }

}
