package com.serenity.api.serenity.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TaskExecutor taskExecutor;

    public void sendEmail(final String remetente, final String destinatario, final String titulo, final String conteudo){
        taskExecutor.execute(() -> sendMailSimple(remetente, destinatario, titulo, conteudo));
    }

    private void sendMailSimple(
            final String remetente,
            final String destinatario,
            final String titulo,
            final String conteudo
    ) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(titulo);
            helper.setText(conteudo);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }

        mailSender.send(message);
    }
}

