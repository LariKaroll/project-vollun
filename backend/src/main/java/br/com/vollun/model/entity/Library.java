package br.com.vollun.model.entity;

import br.com.vollun.model.enums.StatusLeitura;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_libray")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID idUser;
    private UUID idBook;
    @Enumerated
    @Column(name = "statusLeitura")
    private StatusLeitura statusLeitura;
}
