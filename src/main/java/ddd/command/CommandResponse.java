package ddd.command;

import ddd.event.DomainEvent;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public abstract class CommandResponse implements Serializable {

    private final List<DomainEvent> events;

    public CommandResponse(List<DomainEvent> events) {
        this.events = events;
    }

    public boolean hasEvents() {
        return !this.events.isEmpty();
    }
}
