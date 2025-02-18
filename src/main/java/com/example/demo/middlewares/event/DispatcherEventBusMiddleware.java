package com.example.demo.middlewares.event;

import ddd.event.DomainEvent;
import ddd.event.EventBusMiddleware;
import ddd.event.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherEventBusMiddleware implements EventBusMiddleware {

    private final Map<Class, List<EventHandler>> eventHandlers;

    public <T extends DomainEvent> DispatcherEventBusMiddleware(List<EventHandler<T>> eventHandlers) {
        this.eventHandlers = new HashMap<>();
        eventHandlers.forEach(eventHandler -> {
            List<EventHandler> existingEventHandlers = this.eventHandlers.get(eventHandler.listenTo());
            if (existingEventHandlers == null) {
                existingEventHandlers = new ArrayList<>();
            }

            existingEventHandlers.add(eventHandler);
            this.eventHandlers.put(eventHandler.listenTo(), existingEventHandlers);
        });
    }

    @Override
    public <T extends DomainEvent> void dispatch(T event) {
        List<EventHandler> eventHandlers = this.eventHandlers.get(event.getClass());

        if (eventHandlers != null) {
            eventHandlers.forEach(eventHandler -> eventHandler.handle(event));
        }
    }
}
