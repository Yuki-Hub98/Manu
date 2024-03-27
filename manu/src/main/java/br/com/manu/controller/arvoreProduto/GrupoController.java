package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.grupo.*;
import br.com.manu.service.arvoreProduto.grupo.GrupoService;
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

    @GetMapping("/search")
    public ResponseEntity<List<GrupoResponse>> getDescricao(@RequestParam("descricao") String request){
        return ResponseEntity.ok(service.getDescricao(request));
    }

    @GetMapping("/search/{familia}")
    public ResponseEntity<List<GrupoResponseFamilia>> getGrupoByFamilia(@PathVariable String familia){
        return ResponseEntity.ok(service.getGrupoByFamilia(familia));
    }

    @PutMapping("/edit/{codigo}")
    public ResponseEntity <GrupoResponse> edit(@PathVariable int codigo, @RequestBody GrupoRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }

    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<GrupoDel>del(@RequestBody GrupoRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo, request));
    }
}
