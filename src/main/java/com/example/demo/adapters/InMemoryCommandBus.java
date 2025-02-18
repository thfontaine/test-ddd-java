package com.example.demo.adapters;

import com.example.demo.middlewares.command.DispatcherCommandBusMiddleware;
import com.example.demo.middlewares.command.ErrorManagementCommandBusMiddleware;
import com.example.demo.middlewares.command.EventDispatcherCommandBusMiddleware;
import com.example.demo.middlewares.command.LoggerCommandBusMiddleware;
import ddd.command.*;
import ddd.event.EventBus;
import ddd.monad.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryCommandBus implements CommandBus {

    private final CommandBusMiddleware first;

    public <T extends Command> InMemoryCommandBus(List<CommandHandler<T>> commandHandlers, EventBus eventBus) {
        this.first = new ErrorManagementCommandBusMiddleware(new LoggerCommandBusMiddleware(
                new EventDispatcherCommandBusMiddleware(new DispatcherCommandBusMiddleware(commandHandlers), eventBus)
        ));
    }

    @Override
    public CommandResponse dispatch(Command command) {
        Result<CommandResponse> result = this.first.dispatch(command);
        return result.getValue();
    }
}
