package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.cor.CorDel;
import br.com.manu.model.arvoreProduto.cor.CorRequest;
import br.com.manu.model.arvoreProduto.cor.CorResponse;
import br.com.manu.service.arvoreProduto.cor.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto/cor")
public class CorController {
    @Autowired
    CorService service;
    @PostMapping
    public ResponseEntity<CorResponse> create(@RequestBody CorRequest request){
        return ResponseEntity.ok(service.create(request));

    }
    @GetMapping
    public  ResponseEntity<List <CorResponse>> findAll(){
        return ResponseEntity.ok(service.getAll());

    }
    @GetMapping("/search")
    public  ResponseEntity<List<CorResponse>> getDescricao(@RequestParam("descricao") String request){
        return  ResponseEntity.ok(service.getDescricao(request));
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<CorResponse> edit(@PathVariable int codigo, @RequestBody CorRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }
    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<CorDel> del(@PathVariable int codigo, @RequestBody CorRequest request){
        return ResponseEntity.ok(service.del(codigo, request));
    }
}
