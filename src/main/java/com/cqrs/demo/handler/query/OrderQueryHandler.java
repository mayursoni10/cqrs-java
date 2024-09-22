package com.cqrs.demo.handler.query;

import com.cqrs.demo.entity.OrderReadModel;
import com.cqrs.demo.model.GetOrderQuery;
import com.cqrs.demo.repository.OrderReadModelRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderQueryHandler {
    private final OrderReadModelRepository readModelRepository;

    public OrderQueryHandler(OrderReadModelRepository readModelRepository) {
        this.readModelRepository = readModelRepository;
    }

    public OrderReadModel handle(GetOrderQuery query) {
        return readModelRepository.findById(query.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
