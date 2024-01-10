package br.com.manu.controller;

import br.com.manu.model.cor.CorRequest;
import br.com.manu.model.cor.CorResponse;
import br.com.manu.service.Cor.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto-cor")
public class CorController {
    @Autowired
    CorService service;
    @PostMapping
    public ResponseEntity<CorResponse> create(@RequestBody CorRequest request){
        return ResponseEntity.ok(service.create(request));

    }

    @GetMapping
    public  ResponseEntity<List <CorResponse>> findAll(){
        return ResponseEntity.ok(service.getAll());

    }
}
