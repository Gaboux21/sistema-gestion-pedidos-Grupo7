package com.grupo7.Sistema.de.Gestion.de.pedidos.security;

import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CustomerUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // Convertir el rol del usuario en una autoridad de Spring Security
        Set<GrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre())
        );

        // Retornar un objeto User de Spring Security
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                authorities
        );
    }
}
