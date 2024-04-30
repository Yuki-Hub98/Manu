package br.com.manu.controller.fichaTecnica;

import br.com.manu.model.fichaTecnica.FichaTecnicaDel;
import br.com.manu.model.fichaTecnica.FichaTecnicaModalItemRecursoEtapa;
import br.com.manu.model.fichaTecnica.FichaTecnicaRequest;
import br.com.manu.model.fichaTecnica.FichaTecnicaResponse;
import br.com.manu.service.fichaTecnica.FichaTecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ficha-tecnica")
public class FichaTecnicaController {
    @Autowired
    FichaTecnicaService service;
    @PostMapping
    public ResponseEntity<FichaTecnicaResponse> create(@RequestBody FichaTecnicaRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<FichaTecnicaResponse>> getAll(){
        return ResponseEntity.ok((service.getAll()));
    }

    @PutMapping("/edit/{codigo}")
    public ResponseEntity<FichaTecnicaResponse> edit(@RequestBody FichaTecnicaRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.edit(request, codigo));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FichaTecnicaResponse>>getParams(@RequestParam("fichaTecnica") String fichaTecnica){
        return ResponseEntity.ok(service.getParams(fichaTecnica));
    }

    @DeleteMapping("/del/{codigo}")
    public ResponseEntity<FichaTecnicaDel> del(@RequestBody FichaTecnicaRequest request, @PathVariable int codigo){
        return ResponseEntity.ok(service.del(request, codigo));
    }
    @GetMapping("/modalItemRecursoEtapa")
    public ResponseEntity<FichaTecnicaModalItemRecursoEtapa> getModalItemRecursoEtapa(){
        return ResponseEntity.ok(service.getModalItemRecursoEtapa());
    }


}
