package br.com.gla.rede_social_livros.repository;

import br.com.gla.rede_social_livros.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAuthorRepository extends JpaRepository<Author, UUID> {
    Author findByName(String Name);
}
