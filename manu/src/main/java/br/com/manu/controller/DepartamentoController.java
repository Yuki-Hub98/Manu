package br.com.manu.controller;

import br.com.manu.model.departamento.DepartamentoRequest;
import br.com.manu.model.departamento.DepartamentoResponse;
import br.com.manu.service.departamento.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto/departamento")
public class DepartamentoController {
    @Autowired
    private DepartamentoService service;
    @PostMapping
    public ResponseEntity<DepartamentoResponse> create(@RequestBody DepartamentoRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
