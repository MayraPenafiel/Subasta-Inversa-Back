package com.examcomplexivo.subastainversaservices.repositories;

import com.examcomplexivo.subastainversaservices.models.Oferta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface OfertaRepository extends CrudRepository<Oferta, Long> {

    Optional<Oferta> findById(Long id);

    Optional<Oferta> findByFecha(Date fecha);

    Optional<Oferta> findByEstado(Boolean estado);

    @Query(value = "SELECT " +
            "ofe.descripcion_oferta, " +
            "ofe.estado_oferta, " +
            "ofe.fecha_oferta, " +
            "ofe.percio_oferta, " +
            "ofe.calificacion_oferta, " +
            "pro.id_proveedor, " +
            "per.nombre, " +
            "per.apellido, " +
            "per.email, " +
            "per.telefono " +
            "FROM " +
            "db_subasta.oferta AS ofe JOIN db_subasta.proveedor AS pro ON ofe.proveedor_id_proveedor = pro.id_proveedor " +
            "JOIN db_subasta.personas AS per ON pro.id_proveedor = per.id_persona " +
            "WHERE  " +
            "per.nombre LIKE %:filtro% " +
            "OR per.apellido LIKE %:filtro% " +
            "OR per.email LIKE %:filtro% " +
            "OR per.telefono LIKE %:filtro%",
            nativeQuery = true)
    Optional<Oferta> findbyProveedor(String filtro);

    @Query(value = "SELECT " +
            "ofe.descripcion_oferta, " +
            "ofe.estado_oferta, " +
            "ofe.fecha_oferta, " +
            "ofe.percio_oferta, " +
            "ofe.calificacion_oferta, " +
            "pro.id_proveedor, " +
            "per.nombre, " +
            "per.apellido, " +
            "per.email, " +
            "per.telefono " +
            "FROM db_subasta.oferta AS ofe " +
            "JOIN db_subasta.proveedor AS pro ON ofe.proveedor_id_proveedor = pro.id_proveedor " +
            "JOIN db_subasta.personas AS per ON pro.id_proveedor = per.id_persona" +
            " WHERE ofe.calificacion_oferta = :filtro")
    Optional<Oferta> findbyCalificaion(String filtro);
}
