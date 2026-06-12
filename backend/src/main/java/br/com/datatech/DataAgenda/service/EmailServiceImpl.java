package br.com.datatech.DataAgenda.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl {


    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(String assunto, String mensagem, String... destinatarios) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatarios);
        email.setSubject(assunto);
        email.setText(mensagem);
        mailSender.send(email);
    }
}
