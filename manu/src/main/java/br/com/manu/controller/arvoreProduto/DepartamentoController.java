package br.com.manu.controller.arvoreProduto;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoDel;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.service.arvoreProduto.departamento.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arvore-produto/departamento")
public class DepartamentoController {
    @Autowired
    private DepartamentoService service;
    @PostMapping
    public ResponseEntity<DepartamentoResponse> create(@RequestBody DepartamentoRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public  ResponseEntity<List<DepartamentoResponse>> getDescricao(@RequestParam("descricao") String request){
        return  ResponseEntity.ok(service.getDescricao(request));
    }

    @PutMapping("/edit/{codigo}")
    public ResponseEntity <DepartamentoResponse> edit(@PathVariable int codigo, @RequestBody DepartamentoRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }

    @DeleteMapping("/del/{codigo}")
    public ResponseEntity <DepartamentoDel> del(@RequestBody DepartamentoRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo, request));
    }

}
