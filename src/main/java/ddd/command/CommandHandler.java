package ddd.command;

import ddd.monad.Result;

public interface CommandHandler<T extends Command> {
    Result<CommandResponse> handle(T command);
    Class<T> listenTo();
}
