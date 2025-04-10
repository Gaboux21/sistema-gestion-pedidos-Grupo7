package com.grupo7.Sistema.de.Gestion.de.pedidos.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.JwtResponse;
import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.LoginDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.UsuarioDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.security.JwtUtil;
import com.grupo7.Sistema.de.Gestion.de.pedidos.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(UsuarioService usuarioService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserDetailsService userDetailsService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok("Login exitoso, JWT generado: " + jwt);
    }
}
