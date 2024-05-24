package com.msvc.usuario.impl;

import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFundException;
import com.msvc.usuario.repository.UsuarioRepository;
import com.msvc.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuarioId);
        return repository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuario() {
        return repository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() -> new ResourceNotFundException("Usuario no encontrado con el id: "+usuarioId));
    }
}
