package com.examcomplexivo.subastainversaservices.services.usuario;

import com.examcomplexivo.subastainversaservices.models.Usuario;
import com.examcomplexivo.subastainversaservices.repositories.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) usuarioRespository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRespository.findById(id).orElse(new Usuario());
    }

    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        return usuarioRespository.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRespository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRespository.deleteById(id);
    }
}
