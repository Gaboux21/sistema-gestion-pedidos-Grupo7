package com.grupo7.Sistema.de.Gestion.de.pedidos.infrastructure.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.RolDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Rol;
import com.grupo7.Sistema.de.Gestion.de.pedidos.application.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody RolDTO rolDTO){
        return new ResponseEntity<>(rolService.crearRol(rolDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles(){
        return ResponseEntity.ok(rolService.listarRoles());
    }

}
