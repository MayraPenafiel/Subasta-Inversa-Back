package com.examcomplexivo.subastainversaservices.repositories;

import com.examcomplexivo.subastainversaservices.models.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

    Optional<Persona> findByEmail(String email);

    Optional<Persona> findById(Long id);
    Optional<Persona> findByNombre(String nombre);
}
