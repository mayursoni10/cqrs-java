package com.cqrs.demo.model;

// Query
public class GetOrderQuery {
    private String orderId;

    // Constructor, getter, and setter
    public GetOrderQuery(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
