package com.example.demo.middlewares.command;

import ddd.command.Command;
import ddd.command.CommandBusMiddleware;
import ddd.command.CommandHandler;
import ddd.command.CommandResponse;
import ddd.monad.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherCommandBusMiddleware implements CommandBusMiddleware {

    private final Map<Class, CommandHandler> commandHandlerMap;

    public <T extends Command> DispatcherCommandBusMiddleware(List<CommandHandler<T>> commandHandlers) {
        commandHandlerMap = new HashMap<>();
        commandHandlers.forEach(commandHandler -> commandHandlerMap.put(commandHandler.listenTo(), commandHandler));
    }

    @Override
    public Result<CommandResponse> dispatch(Command command) {
        CommandHandler commandHandler = this.commandHandlerMap.get(command.getClass());

        if (commandHandler == null) {
            throw new RuntimeException("No command handler found");
        }

        return commandHandler.handle(command);
    }
}
