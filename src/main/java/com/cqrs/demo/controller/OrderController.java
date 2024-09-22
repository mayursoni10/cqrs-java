package com.cqrs.demo.controller;

import com.cqrs.demo.entity.OrderReadModel;
import com.cqrs.demo.handler.command.OrderCommandHandler;
import com.cqrs.demo.handler.query.OrderQueryHandler;
import com.cqrs.demo.model.CreateOrderCommand;
import com.cqrs.demo.model.GetOrderQuery;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderCommandHandler commandHandler;
    private final OrderQueryHandler queryHandler;

    public OrderController(OrderCommandHandler commandHandler, OrderQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public void createOrder(@RequestBody CreateOrderCommand command) {
        commandHandler.handle(command);
    }

    @GetMapping("/{orderId}")
    public OrderReadModel getOrder(@PathVariable String orderId) {
        return queryHandler.handle(new GetOrderQuery(orderId));
    }
}
