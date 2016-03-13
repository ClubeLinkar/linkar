package br.com.clubelinkar.support.event

import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Component

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session

/**
 * Created by felipe on 3/13/16.
 */
@Log4j
@Component
class EventBus implements IEventBus {

    @Autowired
    private ConfigurableApplicationContext context

    @Autowired
    private TimelineEventRepository timelineEventRepository

    @Override
    void publish(IEvent event) {
        log.info("Novo evento >> type: ${event.type.name()}, payload: ${event.payload?.toString()}")
        String destination = event.type.name() + "-destination";
        MessageCreator messageCreator = instantiateMessageCreator(event.payload)
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class)
        TimelineEvent timelineEvent = TimelineEvent.from(event)

        jmsTemplate.send(destination, messageCreator)
        timelineEventRepository.save(timelineEvent)
    }

    private MessageCreator instantiateMessageCreator(def payload) {
        return new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(payload);
            }
        };
    }
}
