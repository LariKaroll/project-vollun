package br.com.gla.rede_social_livros.repository;

import br.com.gla.rede_social_livros.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, UUID> {
}
