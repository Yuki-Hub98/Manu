package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoResponse;
import br.com.manu.model.arvoreProduto.familia.*;
import br.com.manu.service.arvoreProduto.familia.FamiliaService;
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

    @GetMapping("/search")
    public  ResponseEntity<List<FamiliaResponse>> getDescricao(@RequestParam("descricao") String request){
        return  ResponseEntity.ok(service.getDescricao(request));
    }
    @GetMapping("/search/{linha}")
    public  ResponseEntity<List<FamiliaResponseLinha>> getFamiliaByLinha(@PathVariable String linha){
        return  ResponseEntity.ok(service.getFamiliaByLinha(linha));
    }
    @PutMapping("/edit")
    public ResponseEntity<FamiliaResponse> edit(@RequestBody FamiliaEdit request){
        return ResponseEntity.ok(service.edit(request));
    }
    @DeleteMapping("/del")
    public ResponseEntity<FamiliaDel> del(@RequestParam ("descricao") String descricao, @RequestBody FamiliaResquest request){
        return ResponseEntity.ok(service.del(descricao, request));
    }
}
