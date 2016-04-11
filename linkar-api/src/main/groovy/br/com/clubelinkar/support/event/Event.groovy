package br.com.clubelinkar.support.event

/**
 * Created by felipe on 3/13/16.
 */
abstract class Event implements IEvent {
    def payload
    EventType type
}
