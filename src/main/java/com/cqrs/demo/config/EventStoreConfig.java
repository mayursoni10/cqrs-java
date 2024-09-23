package com.cqrs.demo.config;

import com.cqrs.demo.mongo.repository.EventStore;
import com.cqrs.demo.mongo.repository.impl.EventStoreDecorator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventStoreConfig {

    @Bean
    public EventStore eventStore(EventStore eventStore, ApplicationEventPublisher eventPublisher) {
        return new EventStoreDecorator(eventStore, eventPublisher);
    }
}
