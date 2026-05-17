package br.com.vollun.controller;

import br.com.vollun.model.dto.BookResponseDTO;
import br.com.vollun.model.entity.Book;
import br.com.vollun.model.entity.User;
import br.com.vollun.repository.IBookRepository;
import br.com.vollun.repository.IUserRepository;
import br.com.vollun.services.BookServices;
import br.com.vollun.services.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BookServices bookServices;

    @GetMapping("/my-books")
    public ResponseEntity<List<BookResponseDTO>> listBook(
            org.springframework.security.core.Authentication authentication
    ) {
        List<BookResponseDTO> meusLivros = bookServices.listMyBooks(authentication.getName());
        return ResponseEntity.ok(meusLivros);
    }

//    @PutMapping("/update")
//    public ResponseEntity<?> updateBook(
//            org.springframework.security.core.Authentication authentication
//    ){
//        var userLogado = authentication.getName();
//        var emailVerificado = bookRepository.findByUserEmail(userLogado)
//                .orElseThrow(() -> new RuntimeException("Email logado não encontrado!"));
//
//        List<BookResponseDTO> meusLivros =
//
//        return ResponseEntity.ok("ok");
//    }

    @PostMapping("/file")
    public ResponseEntity<BookResponseDTO> createInputBook(
            @RequestParam("file")MultipartFile file,
            @ModelAttribute BookResponseDTO dados,
            org.springframework.security.core.Authentication authentication
    ) {

        String usernameLogado = authentication.getName();
        User userSystem = userRepository.findByEmail(usernameLogado)
                .orElseThrow(() -> new RuntimeException("Email logado não encontrado no banco!"));

        var urlFile = firebaseStorageService.uploadFile(file);

        var book = new Book();

        book.setTitle(dados.title());
        book.setSinopse(dados.sinopse());
        book.setPublicationDate(dados.publicationDate());
        book.setIdAutor(dados.idAutor());
        book.setGenre(dados.genre());
        book.setUrlPdf(urlFile);
        book.setUser(userSystem);

        Book bookSalvo = bookRepository.save(book);

        return ResponseEntity.ok(new BookResponseDTO(bookSalvo));
    }
}
