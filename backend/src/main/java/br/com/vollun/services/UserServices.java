package br.com.vollun.services;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import br.com.vollun.model.entity.User;
import br.com.vollun.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listUser(){
        return this.userRepository.findAll();
    }

    public User register(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RecursoNaoEncontradoException("Usuário já cadastrado.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RecursoNaoEncontradoException("Email já cadastrado.");
        }

        if (userRepository.findByCpf(user.getCpf()).isPresent()) {
            throw new RecursoNaoEncontradoException("CPF já cadastrado.");
        }

        user.setStatus(true);

        String passwordHashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHashed);

        return userRepository.save(user);
    }

    public User deletUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não cadastrado!"));

        userRepository.deleteById(id);
        return user;
    }

    public User fazerLogin(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Email não encontrado."));


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RecursoNaoEncontradoException("Senha incorreta.");
        }

        return user;
    }
}