package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.linha.*;
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

    @GetMapping("/search")
    public ResponseEntity<List<LinhaResponse>> getDescricao(@RequestParam("descricao") String request){
        return ResponseEntity.ok(service.getDescricao(request));
    }
    @GetMapping("/search/{departamento}")
    public ResponseEntity<List<LinhaResponseDepartamento>> getDescricaoByDepartamento(@PathVariable String departamento){
        return ResponseEntity.ok(service.getDescricaoByDepartamento(departamento));
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<LinhaResponse> edit(@PathVariable int codigo, @RequestBody LinhaRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }

    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<LinhaDel>del(@RequestBody LinhaRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo,request));
    }
}
