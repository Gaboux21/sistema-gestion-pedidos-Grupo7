package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.RolDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Rol;

import java.util.List;

public interface RolService {
    Rol crearRol(RolDTO rolDTO);
    List<Rol> listarRoles();
}

