package com.cqrs.demo.repository;

import com.cqrs.demo.entity.OrderReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReadModelRepository extends JpaRepository<OrderReadModel, String> {
}
