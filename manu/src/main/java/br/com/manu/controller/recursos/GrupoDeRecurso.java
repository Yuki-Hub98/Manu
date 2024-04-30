package br.com.manu.controller.recursos;

import br.com.manu.model.recursos.grupoDeRecurso.*;
import br.com.manu.service.recursos.grupoDeRecurso.GrupoDeRecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos/grupo-de-recursos")
public class GrupoDeRecurso {
    @Autowired
    GrupoDeRecursoService service;

    @PostMapping
    public ResponseEntity<GrupoDeRecursoResponse> create(@RequestBody GrupoDeRecursoRequestSave request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GrupoDeRecursoResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<GrupoDeRecursoResponse>> searchGrupoRecurso(@RequestParam("descricao") String grupoRecurso){
        return ResponseEntity.ok(service.searchGrupoRecurso(grupoRecurso));
    }

    @PutMapping("/edit/{codigo}")
    public ResponseEntity<GrupoDeRecursoResponse> edit(@RequestBody GrupoDeRecursoRequestSave request, @PathVariable int codigo){
        return ResponseEntity.ok(service.edit(codigo, request));
    }

    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<GrupoDeRecursoDel> del(@RequestBody GrupoDeRecursoResponseSimplified request, @PathVariable int codigo){
        return ResponseEntity.ok(service.dell(codigo, request));
    }
}
