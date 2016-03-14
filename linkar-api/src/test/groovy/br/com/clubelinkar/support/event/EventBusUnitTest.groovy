package br.com.clubelinkar.support.event

import br.com.clubelinkar.api.company.Company
import org.junit.Before
import org.mockito.Mockito
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

import static br.com.clubelinkar.api.company.CompanyMother.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.context.ConfigurableApplicationContext

import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner)
class EventBusUnitTest {

    @Mock
    ConfigurableApplicationContext contextMock

    @Mock
    TimelineEventRepository timelineEventRepositoryMock

    @InjectMocks
    EventBus eventBus

    JmsTemplate jmsTemplateMock

    @Before
    public void setup() {
        jmsTemplateMock = mock(JmsTemplate.class)

        when(contextMock.getBean(JmsTemplate.class)).thenReturn(jmsTemplateMock)
    }

    @Test
    public void "publish posta uma mensagem JMS e salva um TimelineEvent"() {
        Company payload = allMotos()
        IEvent event = new CompanyCreatedEvent(allMotos())

        eventBus.publish(event)

        verify(jmsTemplateMock, times(1)).send(anyString(), Mockito.any(MessageCreator.class))
        verify(timelineEventRepositoryMock, times(1)).save(Mockito.any(TimelineEvent.class))
    }


}
