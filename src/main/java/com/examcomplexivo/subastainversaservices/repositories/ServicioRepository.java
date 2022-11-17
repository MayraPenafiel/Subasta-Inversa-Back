package com.examcomplexivo.subastainversaservices.repositories;

import com.examcomplexivo.subastainversaservices.models.Proveedor;
import com.examcomplexivo.subastainversaservices.models.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Long> {
    Optional<Servicio> findByNombre(String nombre_servicio);
    Optional<Servicio> findById(Long id);
}
