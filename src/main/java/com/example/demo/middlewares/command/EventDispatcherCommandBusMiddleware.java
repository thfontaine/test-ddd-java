package com.example.demo.middlewares.command;

import ddd.command.Command;
import ddd.command.CommandBusMiddleware;
import ddd.command.CommandResponse;
import ddd.event.EventBus;
import ddd.monad.Result;

public class EventDispatcherCommandBusMiddleware implements CommandBusMiddleware {

    private CommandBusMiddleware next;
    private EventBus eventBus;

    public EventDispatcherCommandBusMiddleware(CommandBusMiddleware next, EventBus eventBus) {
        this.eventBus = eventBus;
        this.next = next;
    }

    @Override
    public Result<CommandResponse> dispatch(Command command) {
        Result<CommandResponse> result = this.next.dispatch(command);

        return result.flatMap(tCommandResponse -> {
            if (tCommandResponse.hasEvents()) {
                tCommandResponse.getEvents().forEach(domainEvent -> this.eventBus.dispatch(domainEvent));
            }

            return Result.ok(tCommandResponse);
        });
    }
}
