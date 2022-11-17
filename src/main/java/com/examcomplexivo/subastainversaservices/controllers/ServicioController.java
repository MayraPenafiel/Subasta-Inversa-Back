package com.examcomplexivo.subastainversaservices.controllers;

import com.examcomplexivo.subastainversaservices.models.Cliente;
import com.examcomplexivo.subastainversaservices.models.Servicio;
import com.examcomplexivo.subastainversaservices.services.servicio.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioService service;

    @GetMapping("listar")
    public List<Servicio> listar() {
        return service.listar();
    }

    @PostMapping("crear")
    public ResponseEntity<?> crear(@Valid @RequestBody Servicio servicio, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Servicio> existServicio = service.findByNombre(servicio.getNombre());
        if (existServicio.isPresent()) {
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("mensaje", "Este servicio ya se encuentra registrado.")
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(servicio));
    }

    @PutMapping("/editar/{idServicio}")
    public ResponseEntity<?> editarServicio(@PathVariable(name = "idServicio", required = true) Long idServicio,
                                            @Valid @RequestBody Servicio servicio, BindingResult result) {
        try {
            Optional<Servicio> servicioBD = service.findByNombre(servicio.getNombre());
            Optional<Servicio> existServicio = service.findById(idServicio);

            if (existServicio.isPresent()) {
                if (!existServicio.get().equals(servicioBD.get())
                        && existServicio.get().getNombre().equalsIgnoreCase(servicioBD.get().getNombre())) {
                    return ResponseEntity.badRequest().body(
                            Collections.singletonMap("mensaje", "Este servicio ya se encuentra registrado.")
                    );
                }

                existServicio.get().setNombre(servicio.getNombre());
                existServicio.get().setDescripcion_servicio(servicio.getDescripcion_servicio());

                service.guardar(existServicio.get());
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Servicio modificado correctamente."));
            }else {
                return ResponseEntity.status(404).body(Collections.singletonMap("mensaje", "El servicio no existe."));
            }
        } catch (Exception ex) {
            Logger.getLogger(SubastaController.class.getName()).log(Level.SEVERE, "Imposible realizar cambios");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{idServicio}")
    public ResponseEntity<?> eliminarServicio(@PathVariable(name = "idServicio", required = true) Long idServicio){
        try {
            if (service.findById(idServicio).isPresent()){
                service.eliminar(idServicio);
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Servicio eliminado correctamente."));
            } else {
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "El servicio no existe."));
            }
        }catch (Exception ex){
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
