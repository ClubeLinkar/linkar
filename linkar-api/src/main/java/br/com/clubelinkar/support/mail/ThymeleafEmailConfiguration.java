package br.com.clubelinkar.support.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * @author Lennon Jesus
 */
@Configuration
public class ThymeleafEmailConfiguration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void send() throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(new Locale("pt_BR"));
        ctx.setVariable("name", "Lennon Jesus");
        ctx.setVariable("date", new Date());

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        message.setSubject("BLAAAAAA!!!");
        message.setFrom("noreply@clubelinkar.com.br");
        message.setTo("lennonjesus@localhost");

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("test", ctx);
        message.setText(htmlContent, true /* isHtml */);

        // Send email
        this.javaMailSender.send(mimeMessage);
    }

}