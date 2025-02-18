package com.example.demo.middlewares.query;

import ddd.monad.Result;
import ddd.query.Query;
import ddd.query.QueryBusMiddleware;
import ddd.query.QueryHandler;
import ddd.query.QueryResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherQueryBusMiddleware implements QueryBusMiddleware {

    private final Map<Class, QueryHandler> queryHandlerMap;

    public <T extends Query> DispatcherQueryBusMiddleware(List<QueryHandler<T>> queryHandlers) {
        queryHandlerMap = new HashMap<>();
        queryHandlers.forEach(queryHandler -> queryHandlerMap.put(queryHandler.listenTo(), queryHandler));
    }

    @Override
    public Result<QueryResponse> dispatch(Query query) {
        QueryHandler queryHandler = this.queryHandlerMap.get(query.getClass());

        if (queryHandler == null) {
            throw new RuntimeException("No query handler found");
        }

        return queryHandler.handle(query);
    }
}
