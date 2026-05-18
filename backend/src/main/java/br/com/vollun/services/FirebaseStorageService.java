package br.com.vollun.services;

import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    public String uploadFile(MultipartFile file) {
        try {
            var bucket = StorageClient.getInstance().bucket();
            var nameFile = UUID.randomUUID().toString()+"-"+file.getOriginalFilename();
            var fileBruto = file.getInputStream();
            var typeFile = file.getContentType();
            
            var blobSalvo = bucket.create(nameFile, fileBruto, typeFile);

            var urlFormatada = String.format("https://firestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                    blobSalvo.getBucket(), nameFile);
            return urlFormatada;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload do arquivo para o Firebase: " + e.getMessage());
        }
    }
}
