package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.AnexoASO;
import com.serenity.api.serenity.repositories.AnexoASORepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AnexoASOService {
    
//    private ImagemService imagemService;
    private AnexoASORepository anexoASORepository;

    public AnexoASO cadastrar(AnexoASO anexoASO) {
        return anexoASORepository.save(anexoASO);
    }

    public List<AnexoASO> listar() {
        return anexoASORepository.findAll();
    }

    public AnexoASO buscarPorId(UUID id) {
        return anexoASORepository.findById(id).orElseThrow(() -> new NaoEncontradoException("anexoASO"));
    }

    public void deletar(UUID id) {
        AnexoASO anexoASO = buscarPorId(id);
//        imagemService.deletarAnexo(anexoASO.getUrlAnexo());
        anexoASORepository.deleteById(id);
    }

    public AnexoASO atualizar(UUID id, AnexoASO anexoASO) {
        buscarPorId(id);
        anexoASO.setId(id);

        return anexoASORepository.save(anexoASO);
    }     
        
        
        
        
        
        
        
        
        
        
        
        
}
