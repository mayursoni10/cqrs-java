package com.cqrs.demo.mongo.repository;

// Event Store
import com.cqrs.demo.mongo.document.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStore extends MongoRepository<Event, String> {
    List<Event> findByAggregateIdOrderByTimestampAsc(String aggregateId);
}
