package br.com.vollun.repository;

import br.com.vollun.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAuthorRepository extends JpaRepository<Author, UUID> {
    Author findByName(String Name);
}
