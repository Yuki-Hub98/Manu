package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.linha.LinhaRequest;
import br.com.manu.model.arvoreProduto.linha.LinhaResponse;
import br.com.manu.service.arvoreProduto.linha.LinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto/linha")
public class LinhaController {
    @Autowired
    private LinhaService service;
    @PostMapping
    public ResponseEntity<LinhaResponse> create(@RequestBody LinhaRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LinhaResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}