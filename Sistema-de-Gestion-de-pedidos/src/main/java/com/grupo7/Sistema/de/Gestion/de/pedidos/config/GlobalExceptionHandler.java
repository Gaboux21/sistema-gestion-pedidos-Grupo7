package com.grupo7.Sistema.de.Gestion.de.pedidos.config;

import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Pedido.PedidoNotFoundException.class)
    public ResponseEntity<String> handlePedidoNotFoundException(Pedido.PedidoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado."); // 404
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación."); // 401
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso no autorizado."); // 403
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Manejar la excepción y enviar el mensaje personalizado
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    }

}
