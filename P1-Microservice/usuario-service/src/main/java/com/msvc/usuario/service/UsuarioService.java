package com.msvc.usuario.service;

import com.msvc.usuario.entities.Usuario;
import java.util.List;
public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuario();
    Usuario getUsuario (String usuarioId);

}
