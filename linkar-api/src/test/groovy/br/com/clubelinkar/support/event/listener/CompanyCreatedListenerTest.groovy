package br.com.clubelinkar.support.event.listener

import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.support.notification.INotificationService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import javax.jms.ObjectMessage

import static br.com.clubelinkar.api.company.CompanyMother.allMotos
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner)
class CompanyCreatedListenerTest {

    @Mock
    INotificationService notificationServiceMock

    @InjectMocks
    CompanyCreatedListener listener

    @Test
    public void "execute simply delegates payload to notificationService"() {
        ObjectMessage message = mock(ObjectMessage.class)
        Company payload = allMotos()

        when(message.object).thenReturn(payload)

        listener.execute(message)

        verify(notificationServiceMock, times(1)).notifyCompanyCreation(payload)
    }

}
