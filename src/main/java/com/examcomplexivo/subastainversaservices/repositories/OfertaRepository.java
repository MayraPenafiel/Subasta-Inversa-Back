package com.examcomplexivo.subastainversaservices.repositories;

import com.examcomplexivo.subastainversaservices.models.Oferta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
@Repository
public interface OfertaRepository extends CrudRepository<Oferta,Long> {

    Optional<Oferta> findById(Long id);
    Optional<Oferta> findByFecha(Date fecha);
    Optional<Oferta> findByEstado(Boolean estado);
}
