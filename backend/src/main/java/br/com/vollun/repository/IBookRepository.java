package br.com.vollun.repository;

import br.com.vollun.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByUserEmail(String email);
}
