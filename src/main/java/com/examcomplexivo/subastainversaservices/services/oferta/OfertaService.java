package com.examcomplexivo.subastainversaservices.services.oferta;

import com.examcomplexivo.subastainversaservices.models.Oferta;

import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface OfertaService {

    List<Oferta> listar();
    Optional<Oferta> findById(Long id);
    Optional<Oferta> findByFecha(Date fecha);
    Optional<Oferta> findByEstado(Boolean estado);

    Oferta guardar(Oferta oferta);

    void eliminar(Long id);
}
