package com.examcomplexivo.subastainversaservices.controllers;

import com.examcomplexivo.subastainversaservices.models.Cliente;
import com.examcomplexivo.subastainversaservices.models.Proveedor;
import com.examcomplexivo.subastainversaservices.services.proveedor.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("listar")
    public List<Proveedor> listar(){
        return proveedorService.listar();
    }

    @PostMapping("crear")
    public ResponseEntity<?> crear(@Valid @RequestBody Proveedor proveedor, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        if (proveedorService.findByEmail(proveedor.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("Mensaje", "Este email ya esta en uso.")
            );
        }
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proveedorService.crear(proveedor));
    }

    @PutMapping("/editar/{idProveedor}")
    public ResponseEntity<?> actualizar(@PathVariable(name = "idProveedor", required = true) Long idProveedor,
                                        @Valid @RequestBody Proveedor proveedor, BindingResult result){
        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Proveedor> optionalProveedor = proveedorService.findById(idProveedor);
        if(optionalProveedor.isPresent()){
            Proveedor p = optionalProveedor.get();
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crear(p));
        }

        return ResponseEntity.notFound().build();
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
