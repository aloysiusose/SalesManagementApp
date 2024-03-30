package dev.aloysius.SalesManagementSystem.Controllers;

import dev.aloysius.SalesManagementSystem.CustomExceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler
    public ResponseEntity<String> handleException(UserAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(UsernameNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
