package br.com.clubelinkar.support.event.listener

import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.support.notification.INotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

import javax.jms.ObjectMessage

@Component
class UserCreatedListener {

    @Autowired
    INotificationService notificationService

    @JmsListener(destination = "USER_CREATED-destination")
    public void execute(ObjectMessage payload) {
        User newUser = payload.object
        notificationService.notifyUserCreation(newUser)
    }
}
