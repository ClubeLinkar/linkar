package br.com.clubelinkar.support.notification

import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.support.mail.IMailService
import br.com.clubelinkar.support.mail.Mail
import br.com.clubelinkar.support.mail.MailTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by felipe on 3/13/16.
 */
@Service
class NotificationService implements INotificationService {

    @Autowired
    IMailService mailService

    @Override
    void notifyCompanyCreation(Company newCompany) {
        Mail email = new Mail()
                .from("noreply@clubelinkar.com.br") // FIXME
                .to(newCompany.getEmail())
                .subject("Sua loja está na Linkar!") // FIXME
                .template(MailTemplate.STORE_REGISTRATION)
                .addParameter("name", newCompany.getName()); // FIXME

        mailService.send(email);
    }
}
