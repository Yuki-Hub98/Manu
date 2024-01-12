package br.com.manu.controller;

import br.com.manu.model.familia.FamiliaResponse;
import br.com.manu.model.familia.FamiliaResquest;
import br.com.manu.service.familia.FamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto/familia")
public class FamiliaController {
    @Autowired
    private FamiliaService service;

    @PostMapping
    public ResponseEntity<FamiliaResponse> create(@RequestBody FamiliaResquest resquest){
       return ResponseEntity.ok(service.create(resquest));
    }

    @GetMapping
    public ResponseEntity<List<FamiliaResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
