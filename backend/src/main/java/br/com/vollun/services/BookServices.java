package br.com.vollun.services;

import br.com.vollun.model.dto.BookResponseDTO;
import br.com.vollun.model.entity.User;
import br.com.vollun.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {
    @Autowired
    private IBookRepository bookRepository;
    private UserServices userServices;

    public List<BookResponseDTO> listMyBooks(String email) {
        var listaDeLivros = bookRepository.findByUserEmail(email);
        var listBook = listaDeLivros.stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getSinopse(),
                        book.getPublicationDate(),
                        book.getIdAutor()
                ))
                .toList();
        return listBook;
    }
}
