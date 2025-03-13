package com.serenity.api.serenity.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Anexo;
import com.serenity.api.serenity.repositories.AnexoRepository;
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
public class AnexoService {
    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private final AnexoRepository anexoRepository;

    public Anexo anexar(MultipartFile file) {
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

            Anexo anexo = new Anexo();
            anexo.setNome(blobClient.getBlobName());
            anexo.setUrl(blobClient.getBlobUrl());

            return anexo;
        } catch (IOException ioe) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
    }

    public Anexo cadastrar(MultipartFile file, int tipoArquivo) {
        Anexo anexo = anexar(file);
        anexo.setTipoArquivo(tipoArquivo);
        return anexoRepository.save(anexo);
    }

    public Anexo atualizar(MultipartFile file, UUID id) {
        Anexo oldAnexo = buscarPorId(id);
        deletarAnexo(oldAnexo.getNome());

        Anexo newAnexo = anexar(file);
        newAnexo.setId(id);

        return anexoRepository.save(newAnexo);
    }

    public Anexo buscarPorId(UUID id) {
        return anexoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Anexo"));
    }

    public void deletar(UUID id) {
        Anexo anexo = buscarPorId(id);
        deletarAnexo(anexo.getNome());

        anexoRepository.deleteById(id);
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
