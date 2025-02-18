package com.example.demo.domain;

import ddd.domain.Entity;
import ddd.domain.Id;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Order extends Entity {
    private final DeliveryLocation deliveryLocation;

    private Order(Id id, Instant createdAt, String createdBy, Instant updatedAt, String updatedBy, DeliveryLocation deliveryLocation) {
        super(id, createdAt, createdBy, updatedAt, updatedBy);
        this.deliveryLocation = deliveryLocation;
    }

    public static Order init(DeliveryLocation deliveryLocation, String createdBy) {
        Instant now = Instant.now();
        return new Order(Id.create("abc"), now, createdBy, now, createdBy, deliveryLocation);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id().toString() +
                ", deliveryLocation=" + deliveryLocation +
                '}';
    }
}
