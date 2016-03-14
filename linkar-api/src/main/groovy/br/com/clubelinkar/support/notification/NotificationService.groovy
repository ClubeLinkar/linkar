package br.com.clubelinkar.support.notification

import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.support.mail.IMailService
import br.com.clubelinkar.support.mail.Mail
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
        Mail email = Mail.newCompany()
                .to(newCompany.email)
                .addParameter("name", newCompany.name) // FIXME

        mailService.send(email);
    }

    @Override
    void notifyUserCreation(User newUser) {
        Mail email = Mail.newUser()
                .to(newUser.email)
                .addParameter("name", newUser.name) // FIXME

        mailService.send(email);
    }
}
