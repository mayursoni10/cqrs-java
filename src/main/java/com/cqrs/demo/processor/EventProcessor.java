package com.cqrs.demo.processor;

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
