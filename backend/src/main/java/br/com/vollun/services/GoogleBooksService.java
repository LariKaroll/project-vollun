package br.com.vollun.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksService {

    @Value("${app.google.books.key:}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Object buscarLivrosExternos(String termo) {
        var url = "https://www.googleapis.com/books/v1/volumes?q="+ termo +"&key="+apiKey;

        try {
            var response = restTemplate.getForEntity(url, Object.class);

            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
