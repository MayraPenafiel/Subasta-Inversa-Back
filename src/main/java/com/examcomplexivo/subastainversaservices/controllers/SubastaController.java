package com.examcomplexivo.subastainversaservices.controllers;

import com.examcomplexivo.subastainversaservices.models.Subasta;
import com.examcomplexivo.subastainversaservices.services.subasta.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Controller
@RequestMapping("/subasta")
public class SubastaController {

    @Autowired
    private SubastaService subastaService;

    @GetMapping("listar")
    public List<Subasta> listar() {
        return subastaService.listar();
    }

    @PostMapping("crear")
    public ResponseEntity<?> crear(@Valid @RequestBody Subasta subasta, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        //TODO AGREGAR VALIDACION DE QUE EXISTA EL CLIENTE
        //TODO AGREGAR VALIDACION DE QUE NO VENGA SETEADO LOS VALORES DE OFERTAS
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subastaService.guardar(subasta));
    }

    @PutMapping("/editar/{idSubasta}")
    public ResponseEntity<?> editarSubasta(@PathVariable(name = "idSubasta", required = true)Long idSubasta,
                                                   @RequestBody Subasta subasta){
        try{
            if (subastaService.findById(idSubasta).isPresent()) {
                subasta.setIdSubasta(idSubasta);
                subastaService.guardar(subasta);
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Subasta modificada correctamente."));
            }else{
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "La subasta no existe."));
            }
        }catch(Exception ex){
            Logger.getLogger(SubastaController.class.getName()).log(Level.SEVERE,"NO SE PUDO EDITAR");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{idSubasta}")
    public ResponseEntity<?> eliminarSubasta (@PathVariable(name = "idSubasta", required = true)String idSubasta){
        try{
            if (subastaService.findById(Long.parseLong(idSubasta)).isPresent()) {
                subastaService.eliminar(Long.parseLong(idSubasta));
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Subasta eliminada correctamente."));
            }else{
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "La subasta no existe."));
            }
        }catch(Exception ex){
            Logger.getLogger(SubastaController.class.getName()).log(Level.SEVERE, "NO SE PUDO ELIMINAR");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("mensaje", "No se pudo eliminar."));
        }
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo" + err.getField()
                    + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
