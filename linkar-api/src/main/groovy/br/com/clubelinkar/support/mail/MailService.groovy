package br.com.clubelinkar.support.mail

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine

import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

/**
 * @author Lennon Jesus
 */
@Service
public class MailService implements IMailService {

    private final Log logger = LogFactory.getLog(this.getClass())

    @Autowired
    private JavaMailSender javaMailSender

    @Autowired
    private TemplateEngine templateEngine

    public void send(Mail email) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage()
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8")

            message.setSubject(email.getSubject())
            message.setFrom(email.getFrom())
            message.setTo(email.getTo())

            String htmlContent = templateEngine.process(email.getTemplate().getName(), email.getParameters())
            message.setText(htmlContent, true /* isHtml */)

            javaMailSender.send(mimeMessage)

        } catch (MessagingException e) {
            logger.warn("Problema ao enviar e-mail: " + e.getCause())
        }
    }

}
