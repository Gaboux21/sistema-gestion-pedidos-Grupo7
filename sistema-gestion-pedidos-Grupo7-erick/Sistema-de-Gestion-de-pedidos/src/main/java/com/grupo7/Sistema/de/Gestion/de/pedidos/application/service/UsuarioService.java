package com.grupo7.Sistema.de.Gestion.de.pedidos.application.service;


import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.UsuarioDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crearUsuario(UsuarioDTO usuarioDTO);
    Usuario obtenerUsuarioPorId(Long id);
    List<Usuario> listarUsuarios();
    void eliminarUsuario(Long id);
}
