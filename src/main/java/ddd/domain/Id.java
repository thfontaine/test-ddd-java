package ddd.domain;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Id {
    private final String value;

    private Id(String value) {
        this.value = value;
    }

    public static Id init() {
        return new Id(UUID.randomUUID().toString());
    }

    public static Id create(String value) {
        return new Id(value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return this.value.equals(id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
