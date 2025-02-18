package ddd.event;

public interface EventHandler<T extends DomainEvent> {
    void handle(T event);
    Class<T> listenTo();
}
