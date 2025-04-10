package com.grupo7.Sistema.de.Gestion.de.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@EnableMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class SistemaDeGestionDePedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeGestionDePedidosApplication.class, args);
	}

}
