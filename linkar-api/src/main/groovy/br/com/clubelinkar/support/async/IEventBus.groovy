package br.com.clubelinkar.support.async

/**
 * Created by felipe on 3/13/16.
 */
interface IEventBus {
    void publish(EventType eventType, def payload)
}
