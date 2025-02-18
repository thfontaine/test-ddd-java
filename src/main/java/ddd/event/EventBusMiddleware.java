package ddd.event;

public interface EventBusMiddleware {
    <T extends DomainEvent> void dispatch (T event);
}
