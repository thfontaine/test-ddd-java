package com.example.demo.middlewares.command;

import com.example.demo.exceptions.ApplicationException;
import ddd.command.Command;
import ddd.command.CommandBusMiddleware;
import ddd.command.CommandResponse;
import ddd.monad.Result;

public class ErrorManagementCommandBusMiddleware implements CommandBusMiddleware {

    private CommandBusMiddleware next;

    public ErrorManagementCommandBusMiddleware(CommandBusMiddleware next) {
        this.next = next;
    }

    @Override
    public Result<CommandResponse> dispatch(Command command) {
        Result<CommandResponse> result = this.next.dispatch(command);

        if (result.isError()) {
            throw new ApplicationException(result.getError());
        }

        return result;
    }
}
