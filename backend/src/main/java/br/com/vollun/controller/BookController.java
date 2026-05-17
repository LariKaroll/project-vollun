package br.com.vollun.controller;

import br.com.vollun.model.dto.BookRequest;
import br.com.vollun.model.entity.Book;
import br.com.vollun.repository.IBookRepository;
import br.com.vollun.services.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/file")
    public ResponseEntity<Book> createInputBook(
            @RequestParam("file")MultipartFile file,
            @ModelAttribute BookRequest dados
    ) {
        var urlFile = firebaseStorageService.uploadFile(file);

        var book = new Book();

        book.setTitle(dados.title());
        book.setSinopse(dados.sinopse());
        book.setPublicationDate(dados.publicationDate());
        book.setIdAutor(dados.idAutor());
        book.setGenre(dados.genre());

        book.setUrlPdf(urlFile);
        Book bookSalvo = bookRepository.save(book);

        return ResponseEntity.ok(bookSalvo);
    }
}
