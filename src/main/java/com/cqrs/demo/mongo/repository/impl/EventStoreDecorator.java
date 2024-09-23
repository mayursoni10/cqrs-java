package com.cqrs.demo.mongo.repository.impl;

import com.cqrs.demo.model.NewEventStoredEvent;
import com.cqrs.demo.mongo.document.Event;
import com.cqrs.demo.mongo.repository.EventStore;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class EventStoreDecorator implements EventStore {
    private final EventStore delegate;
    private final ApplicationEventPublisher eventPublisher;

    public EventStoreDecorator(EventStore delegate, ApplicationEventPublisher eventPublisher) {
        this.delegate = delegate;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public <S extends Event> S save(S entity) {
        S savedEvent = delegate.save(entity);
        eventPublisher.publishEvent(new NewEventStoredEvent(savedEvent));
        return savedEvent;
    }

    // We only need to implement the methods we want to customize.
    // For all other methods, we simply delegate to the original implementation:

    @Override
    public List<Event> findByAggregateIdOrderByTimestampAsc(String aggregateId) {
        return delegate.findByAggregateIdOrderByTimestampAsc(aggregateId);
    }

    // Delegate all other methods
    @Override
    public <S extends Event> List<S> saveAll(Iterable<S> entities) {
        return delegate.saveAll(entities);
    }

    @Override
    public List<Event> findAll() {
        return List.of();
    }

    @Override
    public List<Event> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Event entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Event> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Event> findById(String id) {
        return delegate.findById(id);
    }

    @Override
    public boolean existsById(String s) {
        return delegate.existsById(s);
    }

    @Override
    public <S extends Event> S insert(S entity) {
        return delegate.insert(entity);
    }

    @Override
    public <S extends Event> List<S> insert(Iterable<S> entities) {
        return delegate.insert(entities);
    }

    @Override
    public <S extends Event> Optional<S> findOne(Example<S> example) {
        return delegate.findOne(example);
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example) {
        return delegate.findAll(example);
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example, Sort sort) {
        return delegate.findAll(example, sort);
    }

    @Override
    public <S extends Event> Page<S> findAll(Example<S> example, Pageable pageable) {
        return delegate.findAll(example, pageable);
    }

    @Override
    public <S extends Event> long count(Example<S> example) {
        return delegate.count(example);
    }

    @Override
    public <S extends Event> boolean exists(Example<S> example) {
        return delegate.exists(example);
    }

    @Override
    public <S extends Event, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return delegate.findBy(example, queryFunction);
    }

    @Override
    public List<Event> findAll(Sort sort) {
        return delegate.findAll(sort);
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return null;
    }
}
