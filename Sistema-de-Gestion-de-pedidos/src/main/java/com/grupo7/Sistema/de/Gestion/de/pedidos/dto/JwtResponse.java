package com.grupo7.Sistema.de.Gestion.de.pedidos.dto;

public class JwtResponse {
    private String token;

    public JwtResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
