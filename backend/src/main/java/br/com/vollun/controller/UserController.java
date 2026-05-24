package br.com.vollun.controller;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import br.com.vollun.model.dto.UserRequestDTO;
import br.com.vollun.model.dto.UserResponseDTO;
import br.com.vollun.model.entity.User;
import br.com.vollun.services.UserServices;
import jakarta.validation.Valid;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO user = userServices.fazerLogin(userRequestDTO.email(), userRequestDTO.password());
            return ResponseEntity.ok(user);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createdUser(@RequestBody @Valid UserRequestDTO dados) {
        try {
            UserResponseDTO response = userServices.register(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

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
