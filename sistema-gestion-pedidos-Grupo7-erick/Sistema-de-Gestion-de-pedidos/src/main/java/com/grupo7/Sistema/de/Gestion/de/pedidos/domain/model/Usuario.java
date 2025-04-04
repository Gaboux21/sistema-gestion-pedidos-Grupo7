package com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Column(unique = true, nullable = false)
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Column(nullable = false)
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe incluir números, letras y un carácter especial")
    private String password;

    @Column(nullable = false)
    private LocalDate signUpDate;

    @Column(nullable = false)
    private Double totalSpent = 0.0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol", nullable = false)
    private Rol rol;

}
