package br.com.vollun.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import br.com.vollun.model.dto.BookResponseDTO;
import br.com.vollun.model.dto.UserRequestDTO;
import br.com.vollun.model.dto.UserResponseDTO;
import br.com.vollun.model.entity.User;
import br.com.vollun.repository.IUserRepository;

@Service
public class UserServices {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<BookResponseDTO> listUserBook(User user) {
        List<BookResponseDTO> bookDTO = user.getBooks().stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getSinopse(),
                        book.getPublicationDate(),
                        book.getIdAutor()))
                .toList();
        return bookDTO;
    }

    public List<User> listUser() {
        return this.userRepository.findAll();
    }

    public Optional<User> listById(UUID id) {
        return userRepository.findById(id);
    }

    public UserResponseDTO register(UserRequestDTO dados) {

        if (userRepository.findByUsername(dados.username()).isPresent()) {
            throw new RecursoNaoEncontradoException("Usuário já cadastrado.");
        }

        if (userRepository.findByEmail(dados.email()).isPresent()) {
            throw new RecursoNaoEncontradoException("Email já cadastrado.");
        }

        if (userRepository.findByCpf(dados.cpf()).isPresent()) {
            throw new RecursoNaoEncontradoException("CPF já cadastrado.");
        }

        User newUser = new User();
        newUser.setName(dados.name());
        newUser.setUsername(dados.username());
        newUser.setEmail(dados.email());
        newUser.setCpf(dados.cpf());
        newUser.setStatus(true);

        String passwordHashed = passwordEncoder.encode(dados.password());
        newUser.setPassword(passwordHashed);

        User userSalvo = userRepository.save(newUser);
        return new UserResponseDTO(
                userSalvo.getId(),
                userSalvo.getName(),
                userSalvo.getEmail(),
                userSalvo.getUsername(),
                List.of());
    }

    public User deletUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não cadastrado!"));

        userRepository.deleteById(id);
        return user;
    }

    public User alterUser(UUID id, User novoUser) {
        return userRepository.findById(id).map(u -> {
            u.setUsername(novoUser.getUsername());
            u.setName(novoUser.getName());
            u.setEmail(novoUser.getEmail());
            u.setPassword(novoUser.getPassword());
            u.setCpf(novoUser.getCpf());
            u.setStatus(novoUser.isStatus());
            return userRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("User não encontrado!"));
    }

    public UserResponseDTO fazerLogin(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Email não encontrado."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RecursoNaoEncontradoException("Senha incorreta.");
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                listUserBook(user));
    }
}