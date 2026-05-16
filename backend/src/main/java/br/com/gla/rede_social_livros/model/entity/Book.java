package br.com.gla.rede_social_livros.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "tb_livro")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String sinopse;
    private Date publicationDate;
    private UUID idAutor;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String urlPdf;
    private String urlCapa;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
