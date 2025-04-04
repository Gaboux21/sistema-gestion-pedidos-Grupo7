package com.grupo7.Sistema.de.Gestion.de.pedidos.application.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.RolDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Rol;

import java.util.List;

public interface RolService {
    Rol crearRol(RolDTO rolDTO);
    List<Rol> listarRoles();
}

