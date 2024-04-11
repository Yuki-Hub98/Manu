package br.com.manu.controller.recursos;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoDel;
import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;
import br.com.manu.model.recursos.cadastroDeRecurso.RecursoResponse;
import br.com.manu.service.recursos.Recurso.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos/recurso")
public class Recurso {
    @Autowired
    RecursoService service;

    @PostMapping
    public ResponseEntity<RecursoResponse> create(@RequestBody RecursoRequest request){
       return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<RecursoResponse>> geAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/search")
    public ResponseEntity<List<RecursoResponse>> searchRecurso(@RequestParam("descricao") String recurso){
        return ResponseEntity.ok(service.searchRecurso(recurso));
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<RecursoResponse> edit(@PathVariable int codigo, @RequestBody RecursoRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }
    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<RecursoDel> del(@PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo));
    }

}
