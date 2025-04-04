package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.RolDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Rol;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepo;

    @Override
    public Rol crearRol(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setNombre(rolDTO.getNombre());
        return rolRepo.save(rol);
    }

    @Override
    public List<Rol> listarRoles() {
        return rolRepo.findAll();
    }
}
