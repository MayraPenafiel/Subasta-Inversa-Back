package com.examcomplexivo.subastainversaservices.controllers;

import com.examcomplexivo.subastainversaservices.models.Cliente;
import com.examcomplexivo.subastainversaservices.models.Proveedor;
import com.examcomplexivo.subastainversaservices.security.entity.Rol;
import com.examcomplexivo.subastainversaservices.security.enums.RolNombre;
import com.examcomplexivo.subastainversaservices.security.service.rol.RolService;
import com.examcomplexivo.subastainversaservices.services.proveedor.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/auth/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private RolService rolService;
    @Autowired
    PasswordEncoder passwordEncoder;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("listar")
    public List<Proveedor> listar(){
        return proveedorService.listar();
    }

    @GetMapping("listar/{filtro}")
    public List<Proveedor> listarByFiltro(@PathVariable(name = "filtro", required = true) String filtro){
        return proveedorService.findByFiltros(filtro);
    }
    @GetMapping("listar/servicio/{servicio}")
    public List<Proveedor> listarByServicio(@PathVariable(name = "servicio", required = true) String servicio){
        return proveedorService.findByServicio(servicio.toLowerCase());
    }
    @PreAuthorize("hasAnyRole('ADMIN','PROVEEDOR')")
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
        proveedor.getUsuario().setContraseniaUsuario(passwordEncoder.encode(proveedor.getUsuario().getContraseniaUsuario()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.findByRolNombre(RolNombre.ROLE_PROVEEDOR).get());
        proveedor.getUsuario().setRoles(roles);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proveedorService.crear(proveedor));
    }
    @PreAuthorize("hasAnyRole('ADMIN','PROVEEDOR')")
    @PutMapping("/editar/{idProveedor}")
    public ResponseEntity<?> actualizar(@PathVariable(name = "idProveedor", required = true) Long idProveedor,
                                        @Valid @RequestBody Proveedor proveedor, BindingResult result){
        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Proveedor> optionalProveedor = proveedorService.findById(idProveedor);
        if(optionalProveedor.isPresent()){

            optionalProveedor.get().setNombre(proveedor.getNombre());
            optionalProveedor.get().setApellido(proveedor.getApellido());
            optionalProveedor.get().setEmail(proveedor.getEmail());
            optionalProveedor.get().setTelefono(proveedor.getTelefono());
            optionalProveedor.get().setDireccion(proveedor.getDireccion());
            optionalProveedor.get().setAnios_experiencia(proveedor.getAnios_experiencia());
            optionalProveedor.get().setServicios(proveedor.getServicios());

            return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crear(optionalProveedor.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVEEDOR')")
    @DeleteMapping("/eliminar/{idProveedor}")
    public ResponseEntity<?> eliminarServicio(@PathVariable(name = "idProveedor", required = true) Long idProveedor) {
        try {
            if (proveedorService.findById(idProveedor).isPresent()) {
                proveedorService.eliminar(idProveedor);
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Proveedor eliminado correctamente."));
            } else {
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "El proveedor no existe."));
            }
        } catch (Exception ex) {
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
