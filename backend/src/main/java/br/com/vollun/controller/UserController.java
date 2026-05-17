package br.com.vollun.controller;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import br.com.vollun.model.dto.LoginRequest;
import br.com.vollun.model.dto.RegisterRequest;
import br.com.vollun.model.entity.User;
import br.com.vollun.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userServices.fazerLogin(loginRequest.email(), loginRequest.password());
            return ResponseEntity.ok(user);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createdUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User registerUser = userServices.register(registerRequest.user());
            return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","Usuário cadastrado com sucesso!"));
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<User> listUser(){
        return userServices.listUser();
    }

    @DeleteMapping("/delet/{id}")
    public ResponseEntity deletarUsuario(@PathVariable UUID id){
        var userDelet = userServices.deletUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDelet);
    }
}
