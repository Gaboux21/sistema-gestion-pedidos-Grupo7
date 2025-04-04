package com.grupo7.Sistema.de.Gestion.de.pedidos.application.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.RolDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Rol;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol crearRol(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setNombre(rolDTO.getNombre());
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}
