package br.com.manu.controller.cadastroDeRecurso;

import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoDel;
import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoRequest;
import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoResponse;
import br.com.manu.service.cadastroDeRecurso.CadastroDeRecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro-de-recurso")
public class CadastroDeRecurso {
    @Autowired
    CadastroDeRecursoService service;

    @PostMapping
    public ResponseEntity<CadastroDeRecursoResponse> create(@RequestBody CadastroDeRecursoRequest request){
       return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<CadastroDeRecursoResponse>> geAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<CadastroDeRecursoResponse> edit(@PathVariable int codigo, @RequestBody CadastroDeRecursoRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }
    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<CadastroDeRecursoDel> del(@PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo));
    }

}
