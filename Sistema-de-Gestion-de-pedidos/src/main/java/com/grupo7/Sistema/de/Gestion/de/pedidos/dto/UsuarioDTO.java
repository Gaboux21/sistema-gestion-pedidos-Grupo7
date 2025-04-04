package com.grupo7.Sistema.de.Gestion.de.pedidos.dto;

import java.time.LocalDate;
import java.util.Set;

public class UsuarioDTO {

    private String nombre;
    private String email;
    private String password;
    private LocalDate signUpdate;
    private Double totalSpent;
    private Long rolId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getSignUpdate() {
        return signUpdate;
    }

    public void setSignUpdate(LocalDate signUpdate) {
        this.signUpdate = signUpdate;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long roleId) {
        this.rolId = roleId;
    }
}
