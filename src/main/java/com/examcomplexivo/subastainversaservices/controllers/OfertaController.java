package com.examcomplexivo.subastainversaservices.controllers;

import com.examcomplexivo.subastainversaservices.models.Oferta;
import com.examcomplexivo.subastainversaservices.services.oferta.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

//@RestController
public class OfertaController {

//    @Autowired
//    private OfertaService ofertaService;
//
//    @GetMapping
//    public List<Oferta> listar(){
//        return ofertaService.listar();
//    }
//
//    @PostMapping
//    public ResponseEntity<?> crear(@Valid @RequestBody Oferta oferta, BindingResult result){
//        if(result.hasErrors()){
//            return validar(result);
//        }
//        return  ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.save(oferta));
//    }
//
//    @GetMapping("{/id}") //Buscar por id
//    public ResponseEntity<?> listarId(@PathVariable Long id){
//        Optional<Oferta> ofertaOptional=ofertaService.findById(id);
//        if (ofertaOptional.isPresent()) {
//
//            return ResponseEntity.ok(ofertaOptional.get());
//        }
//        return ResponseEntity.badRequest().body(
//                Collections.singletonMap("Mensaje","No se ha encontrado ninguna oferta")
//        );
//    }
//
//    @GetMapping("{/fecha}") //Buscar por fecha
//    public ResponseEntity<?> listarFecha(@PathVariable Date fecha){
//        Optional<Oferta> ofertaOptional=ofertaService.findByDate(fecha);
//        if (ofertaOptional.isPresent()) {
//
//            return ResponseEntity.ok(ofertaOptional.get());
//        }
//        return ResponseEntity.badRequest().body(
//                Collections.singletonMap("Mensaje","No se ha encontrado ninguna oferta")
//        );
//    }
//
//    @GetMapping("{/estado}") //Buscar por id
//    public ResponseEntity<?> listarEstado(@PathVariable Boolean estado){
//        Optional<Oferta> ofertaOptional=ofertaService.findByEstado(estado);
//        if (ofertaOptional.isPresent()) {
//
//            return ResponseEntity.ok(ofertaOptional.get());
//        }
//        return ResponseEntity.badRequest().body(
//                Collections.singletonMap("Mensaje","No se ha encontrado ninguna oferta")
//        );
//    }
//    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
//        Map<String, String> errores = new HashMap<>();
//        result.getFieldErrors().forEach(err -> {
//            errores.put(err.getField(), "El campo" + err.getField()
//                    + " " + err.getDefaultMessage());
//        });
//        return ResponseEntity.badRequest().body(errores);
//    }

}
