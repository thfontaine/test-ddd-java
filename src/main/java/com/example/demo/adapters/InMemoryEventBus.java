package com.example.demo.adapters;

import com.example.demo.middlewares.event.DispatcherEventBusMiddleware;
import ddd.event.DomainEvent;
import ddd.event.EventBus;
import ddd.event.EventBusMiddleware;
import ddd.event.EventHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryEventBus implements EventBus {

    private final EventBusMiddleware first;

    public <T extends DomainEvent> InMemoryEventBus(List<EventHandler<T>> eventHandlers) {
        this.first = new DispatcherEventBusMiddleware(eventHandlers);
    }

    @Override
    public <T extends DomainEvent> void dispatch(T event) {
        this.first.dispatch(event);
    }
}
