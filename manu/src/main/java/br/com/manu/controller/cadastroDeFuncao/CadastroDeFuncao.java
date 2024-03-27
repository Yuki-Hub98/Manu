package br.com.manu.controller.cadastroDeFuncao;

import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoDel;
import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoRequest;
import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoResponse;
import br.com.manu.service.cadastroDeFuncao.CadastroDeFuncaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro-de-funcao")
public class CadastroDeFuncao {
    @Autowired
    CadastroDeFuncaoService service;

    @PostMapping
    public ResponseEntity<CadastroDeFuncaoResponse> create(@RequestBody CadastroDeFuncaoRequest request){
       return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<CadastroDeFuncaoResponse>> geAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<CadastroDeFuncaoResponse> edit(@PathVariable int codigo, @RequestBody CadastroDeFuncaoRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }
    @DeleteMapping("del/{codigo}")
    public ResponseEntity<CadastroDeFuncaoDel> del(@PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo));
    }

}
