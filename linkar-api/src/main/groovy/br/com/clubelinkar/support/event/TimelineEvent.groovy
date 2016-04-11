package br.com.clubelinkar.support.event

import com.google.gson.Gson
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.NotNull
import java.time.LocalDate

@Document
class TimelineEvent {

    @Id
    String id

    @NotNull
    EventType eventType

    @NotNull
    LocalDate dateCreated

    String entity

    String json

    static TimelineEvent from(IEvent event){
        return new TimelineEvent(
                entity: event.payload.class.simpleName,
                eventType: event.type,
                json: new Gson().toJson(event.payload),
                dateCreated: LocalDate.now()
        )
    }
}
