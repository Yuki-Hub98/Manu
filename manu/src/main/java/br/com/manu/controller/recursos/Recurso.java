package br.com.manu.controller.recursos;

import br.com.manu.model.recursos.recurso.RecursoDel;
import br.com.manu.model.recursos.recurso.RecursoRequest;
import br.com.manu.model.recursos.recurso.RecursoResponse;
import br.com.manu.service.recursos.recurso.RecursoService;
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
    public ResponseEntity<RecursoDel> del(@RequestBody RecursoRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(request,codigo));
    }

}
