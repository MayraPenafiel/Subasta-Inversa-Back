package com.examcomplexivo.subastainversaservices.services.servicio;

import com.examcomplexivo.subastainversaservices.models.Proveedor;
import com.examcomplexivo.subastainversaservices.models.Servicio;
import com.examcomplexivo.subastainversaservices.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImp implements ServicioService{

    @Autowired
    private ServicioRepository repository;

    @Override
    public List<Servicio> listar() {
        return (List<Servicio>) repository.findAll();
    }

    @Override
    public Servicio guardar(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    public Optional<Servicio> findByNombre(String nombre_servicio) {
        return repository.findByNombre(nombre_servicio);
    }

    @Override
    public Optional<Servicio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        repository.findById(id);
    }
}
