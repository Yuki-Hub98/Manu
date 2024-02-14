package br.com.manu.controller.fonecedor;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;
import br.com.manu.service.fornecedor.FornecedorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorServiceImp service;

    @PostMapping
    public ResponseEntity<FornecedorResponse> create(@RequestBody FornecedorRequest request){
        return ResponseEntity.ok(service.create(request));
    }


}
