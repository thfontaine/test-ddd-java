package ddd.event;

public interface EventBus {
    <T extends DomainEvent> void dispatch (T event);
}
