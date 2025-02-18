package com.example.demo.domain;

import ddd.domain.Id;
import ddd.domain.ValueObject;
import lombok.Getter;

import java.util.Objects;

@Getter
public class DeliveryLocation extends ValueObject {
    private final Id id;
    private final String name;

    public DeliveryLocation(Id id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeliveryLocation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryLocation that = (DeliveryLocation) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
