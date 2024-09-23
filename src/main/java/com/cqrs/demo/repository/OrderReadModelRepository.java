package com.cqrs.demo.repository;

import com.cqrs.demo.entity.OrderReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReadModelRepository extends JpaRepository<OrderReadModel, String> {
}
