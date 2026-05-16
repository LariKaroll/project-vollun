package br.com.vollun.services;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import br.com.vollun.model.entity.Author;
import br.com.vollun.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServices {

    @Autowired
    private IAuthorRepository authorRepository;

    public Author createdAuthor(Author author) {
        if(authorRepository.findByName(author.getName())!= null) {
            throw new RecursoNaoEncontradoException("Author já cadastrado!");
        }
        return authorRepository.save(author);
    }

    public List<Author> listAuthor(Author author) {
        return this.authorRepository.findAll();
    }

    public Author deletAuthor(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Author não cadastrado!"));

        authorRepository.deleteById(id);

        return author;
    }
}
