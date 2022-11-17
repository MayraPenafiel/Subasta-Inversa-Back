package com.examcomplexivo.subastainversaservices.services.servicio;

import com.examcomplexivo.subastainversaservices.models.Proveedor;
import com.examcomplexivo.subastainversaservices.models.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> listar();

    Servicio guardar(Servicio servicio);

    Optional<Servicio> findByNombre(String nombre_servicio);

    Optional<Servicio> findById(Long id);

    void eliminar(Long id);
}
