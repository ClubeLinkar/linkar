package br.com.clubelinkar.support.event

/**
 * Created by felipe on 3/13/16.
 */
interface IEvent {
    def getPayload()
    EventType getType()
}
