package br.com.manu.controller.produto;

import br.com.manu.model.produto.*;
import br.com.manu.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoService service;
    @PostMapping
    public ResponseEntity<ProdutoResponse>create(@RequestBody ProdutoRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<ResponseItem>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProdutoResponse>> getParams(@RequestBody ProdutoRequestParams request) {
        return ResponseEntity.ok(service.getParams(request));

    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<ProdutoDel> del(@PathVariable int id){
        return ResponseEntity.ok(service.del(id));
    }
}
