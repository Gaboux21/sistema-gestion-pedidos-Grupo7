package com.grupo7.Sistema.de.Gestion.de.pedidos.infrastructure.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository.UsuarioRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtils,
                          UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtils;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        String token = jwtUtil.generateToken((UserDetails) usuario);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
