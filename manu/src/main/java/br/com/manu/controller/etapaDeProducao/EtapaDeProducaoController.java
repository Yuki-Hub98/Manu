package br.com.manu.controller.etapaDeProducao;

import br.com.manu.model.etapaDeProducao.EtapaDeProducaoDel;
import br.com.manu.model.etapaDeProducao.EtapaDeProducaoFichaTecnica;
import br.com.manu.model.etapaDeProducao.EtapaDeProducaoRe;
import br.com.manu.service.etapaDeProducao.EtapaDeProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etapa-de-producao")
public class EtapaDeProducaoController {
    @Autowired
    EtapaDeProducaoService service;

    @PostMapping
    public ResponseEntity<EtapaDeProducaoRe> create(@RequestBody EtapaDeProducaoRe request){
        return ResponseEntity.ok(service.crete(request));
    }
    @GetMapping
    public ResponseEntity<List<EtapaDeProducaoRe>> gelAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/fichaTecnica/etapa-de-producao")
    public ResponseEntity<List<EtapaDeProducaoFichaTecnica>> getEtapaFichaTecnica(){
        return ResponseEntity.ok(service.getEtapaFichaTecnica());
    }
    @PutMapping("/edit/{codigo}")
    public ResponseEntity<EtapaDeProducaoRe> edit(@RequestBody EtapaDeProducaoRe request, @PathVariable int codigo){
        return ResponseEntity.ok(service.edit(codigo, request));
    }
    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<EtapaDeProducaoDel> del(@RequestBody EtapaDeProducaoRe request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(codigo, request));
    }
}
