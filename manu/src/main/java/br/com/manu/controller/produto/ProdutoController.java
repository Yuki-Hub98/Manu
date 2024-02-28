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

    @GetMapping("/search")
    public ResponseEntity<List<ResponseItem>> getParams(@RequestParam ("idItem") String idItem,
                                                        @RequestParam ("descricaoItem") String descricaoItem,
                                                        @RequestParam ("codBarra") String codBarra,
                                                        @RequestParam ("departamento") String departamento,
                                                        @RequestParam ("linha") String linha,
                                                        @RequestParam ("familia") String familia,
                                                        @RequestParam ("grupo") String grupo,
                                                        @RequestParam ("fornecedor") String fornecedor,
                                                        @RequestParam ("modelo") String modelo,
                                                        @RequestParam ("tipoProduto") String tipoProduto,
                                                        @RequestParam ("unidadeMedida") String unidadeMedida,
                                                        @RequestParam ("cor") String cor,
                                                        @RequestParam ("especificacao") String especificacao) {

        return ResponseEntity.ok(service.getParams(idItem,descricaoItem,codBarra,departamento,
                linha,familia,grupo,fornecedor,modelo,tipoProduto,unidadeMedida,cor,especificacao));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseItem> edit(@PathVariable int id, @RequestBody ItemsRequestParams request){
        return ResponseEntity.ok(service.edit(id, request));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ProdutoDel> del(@PathVariable int id){
        return ResponseEntity.ok(service.del(id));
    }
}
