package br.com.vollun.config;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;


@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = getClass()
                    .getClassLoader()
                    .getResourceAsStream("firebase-chave.json");

            if(serviceAccount == null) {
                throw new RecursoNaoEncontradoException("Arquivo firebase-chave.json não foi encontrado na pasta resources!");
            }

            FirebaseOptions option = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("project---vollun.firebasestorage.app")
                    .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(option);
                System.out.println("Firebase inicializado com sucesso!");
            }
        } catch (RecursoNaoEncontradoException | IOException e) {
            System.err.println("Erro ao inicializar o Firebase" + e.getMessage());
        }
    }
}
