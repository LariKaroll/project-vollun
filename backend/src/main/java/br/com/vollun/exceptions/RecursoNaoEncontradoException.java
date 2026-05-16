package br.com.vollun.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensage) {
        super(mensage);
    }
}
