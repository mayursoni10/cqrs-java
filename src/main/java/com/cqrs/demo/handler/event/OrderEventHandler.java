package com.cqrs.demo.handler.event;

import com.cqrs.demo.entity.OrderReadModel;
import com.cqrs.demo.mongo.document.Event;
import com.cqrs.demo.repository.OrderReadModelRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderEventHandler {
    private final OrderReadModelRepository readModelRepository;
    private final ObjectMapper objectMapper;

    public OrderEventHandler(OrderReadModelRepository readModelRepository, ObjectMapper objectMapper) {
        this.readModelRepository = readModelRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void handle(Event event) {
        if ("OrderCreated".equals(event.getType())) {
            try {
                JsonNode data = objectMapper.readTree(event.getData());
                OrderReadModel order = new OrderReadModel();
                order.setOrderId(event.getAggregateId());
                order.setProductId(data.get("productId").asText());
                order.setQuantity(data.get("quantity").asInt());
                order.setStatus("CREATED");
                readModelRepository.save(order);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error processing event data", e);
            }
        }
    }
}
