package com.grupo7.Sistema.de.Gestion.de.pedidos.application.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.UsuarioDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Rol;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository.RolRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private RolRepository rolRepo;

    @Override
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setSignUpDate(LocalDate.now());
        usuario.setTotalSpent(0.0);

        Rol rol = RolRepository.buscarPorNombre("USER").orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
