package com.grupo7.Sistema.de.Gestion.de.pedidos.service;


import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.UsuarioDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crearUsuario(UsuarioDTO usuarioDTO);
    Usuario obtenerUsuarioPorId(Long id);
    List<Usuario> listarUsuarios();
    void eliminarUsuario(Long id);
}
