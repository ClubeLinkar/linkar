package br.com.clubelinkar.support.notification

import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.support.mail.IMailService
import br.com.clubelinkar.support.mail.Mail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

import static br.com.clubelinkar.api.company.CompanyMother.allMotos
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify

/**
 * Created by felipe on 3/14/16.
 */
@RunWith(MockitoJUnitRunner)
class NotificationServiceTest {

    @Mock
    IMailService mailServiceMock

    @InjectMocks
    NotificationService notificationService

    @Test
    void "notifyCompanyCreation delegates to mailService"() {
        Company company = allMotos()

        notificationService.notifyCompanyCreation(company)

        verify(mailServiceMock, times(1)).send(Mockito.any(Mail.class))
    }

}
