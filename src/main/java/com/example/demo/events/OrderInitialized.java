package com.example.demo.events;

import ddd.domain.Id;
import ddd.event.DomainEvent;
import lombok.Getter;

@Getter
public class OrderInitialized implements DomainEvent {

    private final Id id;

    public OrderInitialized(Id id) {
        this.id = id;
    }
}
