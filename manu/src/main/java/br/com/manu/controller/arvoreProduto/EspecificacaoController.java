package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoDel;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoRequest;
import br.com.manu.model.arvoreProduto.especificacao.EspecificacaoResponse;
import br.com.manu.service.arvoreProduto.especificacao.EspecificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("arvore-produto/especificacao")
public class EspecificacaoController {
    @Autowired
    EspecificacaoService service;

    @PostMapping
    public ResponseEntity<EspecificacaoResponse> create(@RequestBody EspecificacaoRequest request){
        return ResponseEntity.ok(service.create(request));

    }

    @GetMapping
    public ResponseEntity<List <EspecificacaoResponse>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public  ResponseEntity<List<EspecificacaoResponse>> getDescricao(@RequestParam("descricao") String request){
        return  ResponseEntity.ok(service.getDescricao(request));
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<EspecificacaoResponse> edit(@PathVariable int codigo, @RequestBody EspecificacaoRequest request){
        return ResponseEntity.ok(service.edite(codigo, request));
    }
    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<EspecificacaoDel> del(@RequestBody EspecificacaoRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo, request));
    }
}
