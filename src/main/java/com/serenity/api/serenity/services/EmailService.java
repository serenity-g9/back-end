package com.serenity.api.serenity.services;


import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Email;
import com.serenity.api.serenity.utils.EmailUtil;
import com.serenity.api.serenity.utils.Fila;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Transactional
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final Fila<MimeMessage> fila = new Fila<>(128);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void queueEmail(final String remetente, final String destinatario, final String titulo, final String conteudo, Agendamento agendamento){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("serenity@contato.invalid", "Serenity"));
            helper.setTo(destinatario);
            helper.setSubject(titulo);
            helper.setText(String.format(EmailUtil.TEMPLATE_CONVITE,
                    agendamento.getEscala().getDemanda().getEvento().getNome(),
                    agendamento.getUsuario().getContato().getNome(),
                    agendamento.getEscala().getDemanda().getEvento().getNome(),
                    FuncaoAlocacao.getValor(agendamento.getEscala().getFuncaoEscala()),
                    agendamento.getEscala().getDemanda().getEvento().getNome(),
                    FuncaoAlocacao.getValor(agendamento.getEscala().getFuncaoEscala()),
                    agendamento.getEscala().getDemanda().getEvento().getEndereco().getLocal(),
                    agendamento.getEscala().getDemanda().getEvento().getEndereco().getLogradouro() + ", " + agendamento.getEscala().getDemanda().getEvento().getEndereco().getNumero(),
                    agendamento.getEscala().getDemanda().getInicio().format(formatter),
                    agendamento.getEscala().getDemanda().getInicio().plusHours(agendamento.getEscala().getHorasJornada()).format(formatter)
                    ), true
            );
        } catch (MessagingException e) {
            throw new MailParseException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        fila.insert(message);
    }

    @Async
    public void process() {
        while (!fila.isEmpty()) {
            try {
                mailSender.send(fila.poll());
            } catch (Exception e) {
                throw new MailParseException(e);
            }
        }
    }

    public void sendEmail(final String remetente, final String destinatario, final String titulo, final String conteudo){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(titulo);
            helper.setText(conteudo);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }

    }

    public void queueConfirmacaoParticipacaoEmail(
            final String remetente,
            final String destinatario,
            final String nomeEvento,
            final String endereco,
            final String empresa,
            final LocalDateTime dataEvento
    ){
        MimeMessage message = mailSender.createMimeMessage();
        String conteudo = EmailUtil.TEMPLATE_CONFIRMACAO_PARTICIPACAO
                .replace("{{nomeEvento}}", nomeEvento)
                .replace("{{dataEvento}}", dataEvento.toString())
                .replace("{{endereco}}", endereco)
                .replace("{{empresa}}", empresa);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject("Confirmacao participação Evento");
            helper.setText(conteudo, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }

        fila.insert(message);
    }
}

