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
    @PutMapping("/edit")
    public ResponseEntity<LinhaResponse> edit(@RequestBody LinhaEdit request){
        return ResponseEntity.ok(service.edit(request));
    }

    @DeleteMapping("/del")
    public ResponseEntity<LinhaDel>del(@RequestParam("descricao") String descricao, @RequestBody LinhaRequest request){
        return ResponseEntity.ok(service.del(descricao, request));
    }
}
