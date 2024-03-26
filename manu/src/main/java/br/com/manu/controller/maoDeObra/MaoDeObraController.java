package br.com.manu.controller.maoDeObra;

import br.com.manu.model.maoDeObra.MaoDeObraDel;
import br.com.manu.model.maoDeObra.MaoDeObraRequest;
import br.com.manu.model.maoDeObra.MaoDeObraResponse;
import br.com.manu.service.maoDeObra.MaoDeObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mao-de-obra")
public class MaoDeObraController {
    @Autowired
    MaoDeObraService service;

    @PostMapping
    public ResponseEntity<MaoDeObraResponse> create(@RequestBody MaoDeObraRequest request){
       return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<MaoDeObraResponse>> geAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<MaoDeObraResponse> edit(@PathVariable int codigo, @RequestBody MaoDeObraRequest request){
        return ResponseEntity.ok(service.edit(codigo, request));
    }
    @DeleteMapping("del/{codigo}")
    public ResponseEntity<MaoDeObraDel> del(@PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo));
    }

}
