package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Email;
import com.serenity.api.serenity.services.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails", produces = {"application/json"})
@Tag(name = "email", description = "Endpoint de envio de email")
@SecurityRequirement(name = "Bearer")
public class EmailController {
    @Autowired
   EmailService emailService;

    @PostMapping
    public ResponseEntity<Email> enviaEmail(@RequestBody @Valid Email email){
        emailService.sendEmail(email.getRemetente(), email.getDestinatario(), email.getTitulo(), email.getConteudo());
        return ResponseEntity.ok(email) ;
    }
}
