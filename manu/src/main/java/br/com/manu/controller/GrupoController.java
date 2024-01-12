package br.com.manu.controller;

import br.com.manu.model.grupo.GrupoRequest;
import br.com.manu.model.grupo.GrupoResponse;
import br.com.manu.service.grupo.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto/grupo")
public class GrupoController {
    @Autowired
    private GrupoService service;

    @PostMapping
    public ResponseEntity<GrupoResponse> create(@RequestBody GrupoRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GrupoResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
