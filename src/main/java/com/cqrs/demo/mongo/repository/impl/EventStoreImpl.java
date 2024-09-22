package com.cqrs.demo.mongo.repository.impl;

@Component
public class EventStoreImpl implements EventStore {
    private final MongoRepository<Event, String> mongoRepository;
    private final ApplicationEventPublisher eventPublisher;

    public EventStoreImpl(MongoRepository<Event, String> mongoRepository, ApplicationEventPublisher eventPublisher) {
        this.mongoRepository = mongoRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public <S extends Event> S save(S entity) {
        S savedEvent = mongoRepository.save(entity);
        eventPublisher.publishEvent(new NewEventStoredEvent(savedEvent));
        return savedEvent;
    }

    // Implement other methods from MongoRepository...
}
