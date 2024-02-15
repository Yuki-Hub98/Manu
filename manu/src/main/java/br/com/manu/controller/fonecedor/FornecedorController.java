package br.com.manu.controller.fonecedor;
import br.com.manu.model.fornecedor.FornecedorDel;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;
import br.com.manu.service.fornecedor.FornecedorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorServiceImp service;

    @PostMapping
    public ResponseEntity<FornecedorResponse> create(@RequestBody FornecedorRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponse>>getAll(){
        return ResponseEntity.ok(service.getAll());

    }

    @GetMapping("/search")
    public ResponseEntity<List<FornecedorResponse>>getParams(@RequestParam("nomeFatansia") String requestName,
                                                             @RequestParam("razaoSocial") String requestRazao,
                                                             @RequestParam("cpfCnpj") String requestCpfCnpj){
        return ResponseEntity.ok(service.getParams(requestName, requestRazao, requestCpfCnpj));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<FornecedorResponse> edit(@PathVariable int id, @RequestBody FornecedorRequest request){
        return ResponseEntity.ok(service.edit(id, request));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<FornecedorDel> del(@PathVariable int id){
        return ResponseEntity.ok(service.del(id));
    }


}
