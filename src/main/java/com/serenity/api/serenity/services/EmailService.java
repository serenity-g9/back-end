package com.serenity.api.serenity.services;


import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Email;
import com.serenity.api.serenity.utils.EmailUtil;
import com.serenity.api.serenity.utils.Fila;
import jakarta.mail.MessagingException;
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

@Transactional
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final Fila<MimeMessage> fila = new Fila<>(128);

    public void queueEmail(final String remetente, final String destinatario, final String titulo, final String conteudo, Agendamento agendamento){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(titulo);
            helper.setText(String.format(EmailUtil.TEMPLATE_CONVITE,
                    agendamento.getEscala().getDemanda().getEvento().getNome(),
                    agendamento.getUsuario().getContato().getNome(),
                    agendamento.getEscala().getDemanda().getEvento().getNome(),
                    FuncaoAlocacao.getValor(agendamento.getEscala().getFuncaoEscala()
                    )), true
            );
        } catch (MessagingException e) {
            throw new MailParseException(e);
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
}

