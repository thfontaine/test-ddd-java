package ddd.command;

public interface CommandBus {
    CommandResponse dispatch (Command command);
}
