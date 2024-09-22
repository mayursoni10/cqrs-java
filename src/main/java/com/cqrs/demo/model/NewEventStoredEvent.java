package com.cqrs.demo.model;

import com.cqrs.demo.mongo.document.Event;

public class NewEventStoredEvent {
    private final Event event;

    public NewEventStoredEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
