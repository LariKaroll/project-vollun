package br.com.vollun.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import br.com.vollun.model.entity.User;
import br.com.vollun.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {
    @Autowired
    private IUserRepository userRepository;

    public List<User> listUser(){
        return this.userRepository.findAll();
    }

    public User register(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RecursoNaoEncontradoException("Usuário já cadastrado.");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RecursoNaoEncontradoException("Email já cadastrado.");
        }

        if (userRepository.findByCpf(user.getCpf()) != null) {
            throw new RecursoNaoEncontradoException("CPF já cadastrado.");
        }

        user.setStatus(true);

        var passwordHashed = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
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
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new RecursoNaoEncontradoException("Email não encontrado.");
        }

        var result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        if (!result.verified) {
            throw new RecursoNaoEncontradoException("Senha incorreta.");
        }

        return user;
    }
}
