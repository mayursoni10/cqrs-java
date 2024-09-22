package com.cqrs.demo.mongo.repository;

// Event Store
import com.cqrs.demo.mongo.document.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStore extends MongoRepository<Event, String> {
    List<Event> findByAggregateIdOrderByTimestampAsc(String aggregateId);
}
