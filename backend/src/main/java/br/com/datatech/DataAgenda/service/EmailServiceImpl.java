package br.com.datatech.DataAgenda.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class EmailServiceImpl {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    @Async
    public void enviarEmail(String descricao,String tecnico, String cliente, String data, String sistema,
                                       String...destinatarios) throws Exception {

        DateTimeFormatter entrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter saida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataConvertida = LocalDate.parse(data, entrada);
        String dataFormatada = dataConvertida.format(saida);

        Context context = new Context();
        context.setVariable("cliente", cliente);
        context.setVariable("sistema", sistema);
        context.setVariable("data", dataFormatada);
        context.setVariable("tecnico", tecnico);
        context.setVariable("descricao", descricao);

        String corpoHtml = templateEngine.process("corpo-email", context);

        MimeMessage mensagem = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
        helper.setTo(destinatarios);
        helper.setSubject("Ordem de Manutenção Gerada");
        helper.setText(corpoHtml, true);

        mailSender.send(mensagem);
    }
}

