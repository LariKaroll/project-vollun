package br.com.gla.rede_social_livros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends RuntimeException {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleNotFound(RecursoNaoEncontradoException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
