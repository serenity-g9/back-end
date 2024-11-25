package com.serenity.api.serenity.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Codigo;
import com.serenity.api.serenity.repositories.CodigoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CodigoService {

    private final CodigoRepository codigoRepository;
    private final Random random = new Random();

    public List<Codigo> listar() {
        return codigoRepository.findAll();
    }

    public Codigo cadastrar(Codigo codigo) {
        return codigoRepository.save(codigo);
    }

    public Codigo buscarPorId(UUID id) {
        return codigoRepository.findById(id).orElseThrow(() ->  new NaoEncontradoException("digito"));
    }

    public Codigo buscarPorDigito(String digito) {
        List<Codigo> codigoList = codigoRepository.buscarCodigoPorSequencia(digito);

        if (codigoList.size() > 1) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(505));
        }

        if (codigoList.isEmpty()) {
            throw new NaoEncontradoException("digito");
        }

        return codigoList.get(0);
    }

    public Codigo confirmarCodigo(String digito) {
        Codigo codigo = buscarPorDigito(digito);
        codigo.setHorarioUtilizado(LocalDateTime.now());
        codigo.setImagemQRCode(null);
        return atualizar(codigo.getId(), codigo);
    }

    public Codigo atualizar(UUID id, Codigo codigo) {
        buscarPorId(id);
        codigo.setId(id);

        return codigoRepository.save(codigo);
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        codigoRepository.deleteById(id);
    }

    public Codigo gerarCodigo() {
        String code = String.format("%06d", random.nextInt(1000000));

        byte[] imagemQRCode = gerarQRCode(code);

        Codigo codigo = new Codigo();
        codigo.setDigito(code);
        codigo.setImagemQRCode(imagemQRCode);
        return codigo;
    }

    private byte[] gerarQRCode(String text) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage qrImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }
}
