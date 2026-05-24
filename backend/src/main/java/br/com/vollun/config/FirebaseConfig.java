package br.com.vollun.config;

import br.com.vollun.exceptions.RecursoNaoEncontradoException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class FirebaseConfig {

    @Value("${fire.base.path:}")
    private String fireBaseConfigApi;

    @PostConstruct
    public void initialize() {
        try {
            Path path = Paths.get(fireBaseConfigApi);

            if(!Files.exists(path)){
                throw new RecursoNaoEncontradoException("O arquivo de credenciais do Firebase não foi encontrado em: " + path.toAbsolutePath());
            }
            InputStream serviceAccount = new FileInputStream(path.toFile());

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
