package com.examcomplexivo.subastainversaservices.repositories;

import com.examcomplexivo.subastainversaservices.models.Subasta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubastaRepository extends CrudRepository<Subasta, Long> {
    Optional<Subasta> findById(Long id);
}
