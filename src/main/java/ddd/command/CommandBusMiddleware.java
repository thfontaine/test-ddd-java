package ddd.command;

import ddd.monad.Result;

public interface CommandBusMiddleware {
    Result<CommandResponse> dispatch (Command command);
}
