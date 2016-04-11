package br.com.clubelinkar.support.event.objects

import br.com.clubelinkar.support.event.Event
import br.com.clubelinkar.support.event.EventType

/**
 * Created by felipe on 3/13/16.
 */
class CompanyCreatedEvent extends Event {
    CompanyCreatedEvent(def payload){
        this.type = EventType.COMPANY_CREATED
        this.payload = payload
    }
}
