package br.com.manu.controller.produto;

import br.com.manu.model.produto.*;
import br.com.manu.persistence.entity.produtos.ncm.Ncm;
import br.com.manu.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService service;
    @PostMapping
    public ResponseEntity<List<ResponseItem>>create(@RequestBody ProdutoRequest request){
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

    @GetMapping("/modelo/linha")
    public ResponseEntity<List<ModeloProduto>> getModels(@RequestParam("linha") String linha){
        return ResponseEntity.ok(service.getModels(linha));
    }
    @GetMapping("/produto")
    public ResponseEntity <ProdutoResponse> getProduto(@RequestParam("decricaoProduto") String decricaoProduto, @RequestParam("fornecedor") String fornecedor){
        return ResponseEntity.ok(service.getProduto(decricaoProduto, fornecedor));
    }

    @GetMapping("/produto/edit/{idItem}")
    public ResponseEntity <ProdutoResponse> getProdutoToEdit(@PathVariable int idItem){
        return ResponseEntity.ok(service.getProdutoToEdit(idItem));
    }
    @GetMapping("/lastId")
        public ResponseEntity <lastIdItem> getLastId () {
            return ResponseEntity.ok(service.getLastId());
        }
    @PutMapping("/edit/{id}")
    public ResponseEntity<List<ResponseItem>> edit(@PathVariable int id, @RequestBody ProdutoRequest request){
        return ResponseEntity.ok(service.edit(id, request));
    }

    @GetMapping("/csticms")
    public ResponseEntity<List<ProdutoCstIcmsResponse>> getCstIcms(@RequestParam("origem") String request){
        return ResponseEntity.ok(service.getCstIcms(request));
    }
    @GetMapping("/ncm")
    public ResponseEntity<List<ProdutoNcm>> getNcm(@RequestParam("ncm") String ncm){
        return ResponseEntity.ok(service.getNcm(ncm));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseItem> del(@RequestBody ResponseItem item, @PathVariable int id){
        return ResponseEntity.ok(service.del(id, item));
    }

    @PostMapping("/modelos")
    public ResponseEntity<Modelo>createModelo(@RequestBody Modelo request){
        return ResponseEntity.ok(service.createModelo(request));
    }

    @GetMapping("/modelos")
    public ResponseEntity<List<Modelo>>getAllModelos(){
        return ResponseEntity.ok(service.getAllModelos());
    }
    @PutMapping("/modelos/edit")
    public ResponseEntity<Modelo>editModelo(@RequestBody Modelo request){
        return ResponseEntity.ok(service.editModelo(request));
    }
    @DeleteMapping("/modelos/del")
    public ResponseEntity<Modelo>delModelo(@RequestBody Modelo request){
        return ResponseEntity.ok(service.delModelo(request));
    }

}
