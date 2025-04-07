package com.serenity.api.serenity.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

    private final AmazonS3 amazonS3;
    private final AnexoRepository anexoRepository;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    public Anexo anexar(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String fileName = UUID.randomUUID() + fileExtension;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            PutObjectRequest request = new PutObjectRequest(
                    bucketName,
                    fileName,
                    file.getInputStream(),
                    metadata
            );

            amazonS3.putObject(request);

            Anexo anexo = new Anexo();
            anexo.setNome(fileName);
            anexo.setUrl(amazonS3.getUrl(bucketName, fileName).toString());

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
        try {
            amazonS3.deleteObject(bucketName, nome);
        } catch (AmazonS3Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao deletar arquivo no S3", e);
        }
    }
}
