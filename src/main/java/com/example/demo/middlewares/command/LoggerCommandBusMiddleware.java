package com.example.demo.middlewares.command;

import ddd.command.Command;
import ddd.command.CommandBusMiddleware;
import ddd.command.CommandResponse;
import ddd.monad.Result;

import java.time.Instant;

public class LoggerCommandBusMiddleware implements CommandBusMiddleware {

    private final CommandBusMiddleware next;

    public LoggerCommandBusMiddleware(CommandBusMiddleware next) {
        this.next = next;
    }

    @Override
    public Result<CommandResponse> dispatch(Command command) {
        long startTime = Instant.now().toEpochMilli();
        Result<CommandResponse> result = this.next.dispatch(command);
        long endTime = Instant.now().toEpochMilli();

        System.out.println("Command took " + (endTime - startTime) + " ms");
        return result;
    }
}
