package com.serenity.api.serenity.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Imagem;
import com.serenity.api.serenity.repositories.ImagemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagemService {
    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private final ImagemRepository imagemRepository;

    public Imagem anexar(MultipartFile file) {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();

        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String fileName = UUID.randomUUID() + fileExtension;

        BlobClient blobClient = containerClient.getBlobClient(fileName);

        try {
            blobClient.upload(file.getInputStream(), file.getSize(), true);

            Imagem imagem = new Imagem();
            imagem.setNome(blobClient.getBlobName());
            imagem.setUrl(blobClient.getBlobUrl());

            return imagem;
        } catch (IOException ioe) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
    }

    public Imagem cadastrar(MultipartFile img) {
        return imagemRepository.save(anexar(img));
    }

    public Imagem atualizar(MultipartFile img, UUID id) {
        Imagem oldImagem = buscarPorId(id);
        deletarAnexo(oldImagem.getNome());

        Imagem newImagem = anexar(img);
        newImagem.setId(id);

        return imagemRepository.save(newImagem);
    }

    public Imagem buscarPorId(UUID id) {
        return imagemRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Imagem"));
    }

    public void deletar(UUID id) {
        Imagem imagem = buscarPorId(id);
        deletarAnexo(imagem.getNome());

        imagemRepository.deleteById(id);
    }

    public void deletarAnexo(String nome) {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();

        BlobClient blobClient = containerClient.getBlobClient(nome);
        blobClient.delete();
    }
}
