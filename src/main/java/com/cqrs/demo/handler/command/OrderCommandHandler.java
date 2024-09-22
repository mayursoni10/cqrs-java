package com.cqrs.demo.handler.command;

import com.cqrs.demo.model.CreateOrderCommand;
import com.cqrs.demo.mongo.document.Event;
import com.cqrs.demo.mongo.repository.EventStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderCommandHandler {
    private final EventStore eventStore;

    public OrderCommandHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Transactional
    public void handle(CreateOrderCommand command) {
        Event event = new Event();
        event.setType("OrderCreated");
        event.setAggregateId(command.getOrderId());
        event.setData(String.format("{\"productId\":\"%s\",\"quantity\":%d}",
                command.getProductId(), command.getQuantity()));
        event.setTimestamp(Instant.now());

        eventStore.save(event);
        // The save operation will automatically trigger event processing
    }
}
