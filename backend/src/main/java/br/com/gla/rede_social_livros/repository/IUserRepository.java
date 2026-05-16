package br.com.gla.rede_social_livros.repository;

import br.com.gla.rede_social_livros.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByCpf(String cpf);
}
