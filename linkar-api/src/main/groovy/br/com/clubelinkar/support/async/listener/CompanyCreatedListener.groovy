package br.com.clubelinkar.support.async.listener

import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.support.notification.INotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

import javax.jms.ObjectMessage

/**
 * Created by felipe on 3/13/16.
 */
@Component
class CompanyCreatedListener {

    @Autowired
    INotificationService notificationService

    @JmsListener(destination = "COMPANY_CREATED-destination")
    public void execute(ObjectMessage payload) {
        Company newCompany = payload.object
        notificationService.notifyCompanyCreation(newCompany)
    }
}
