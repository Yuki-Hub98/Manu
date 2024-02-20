package br.com.manu.controller.tipoProduto;

import br.com.manu.model.tipoProduto.TipoProdutoRequest;
import br.com.manu.service.tipoProduto.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoProduto")
public class TipoProdutoController {
    @Autowired
    TipoProdutoService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TipoProdutoRequest request){
        return ResponseEntity.ok(service.create(request));
    }
}
