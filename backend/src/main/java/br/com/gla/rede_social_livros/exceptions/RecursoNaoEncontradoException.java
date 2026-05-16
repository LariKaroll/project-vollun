package br.com.gla.rede_social_livros.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensage) {
        super(mensage);
    }
}
