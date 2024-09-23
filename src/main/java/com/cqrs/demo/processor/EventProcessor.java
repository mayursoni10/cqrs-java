package com.cqrs.demo.processor;

import com.cqrs.demo.handler.event.OrderEventHandler;
import com.cqrs.demo.model.NewEventStoredEvent;
import com.cqrs.demo.mongo.document.Event;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor {
    private final OrderEventHandler eventHandler;

    public EventProcessor(OrderEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @EventListener
    public void onNewEventStored(NewEventStoredEvent newEventStoredEvent) {
        Event event = newEventStoredEvent.getEvent();
        eventHandler.handle(event);
    }
}
