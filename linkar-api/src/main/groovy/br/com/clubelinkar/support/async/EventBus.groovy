package br.com.clubelinkar.support.async

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
    private ConfigurableApplicationContext context;

    @Override
    void publish(EventType eventType, def payload) {
        log.info("Novo evento >> type: ${eventType.name()}, payload: ${payload?.toString()}")
        String destination = eventType.name() + "-destination";
        MessageCreator messageCreator = instantiateMessageCreator(payload)
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        jmsTemplate.send(destination, messageCreator);
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
