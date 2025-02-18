package com.example.demo.adapters;

import com.example.demo.middlewares.query.DispatcherQueryBusMiddleware;
import com.example.demo.middlewares.query.ErrorManagementQueryBusMiddleware;
import com.example.demo.middlewares.query.LoggerQueryBusMiddleware;
import ddd.monad.Result;
import ddd.query.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryQueryBus implements QueryBus {

    private final QueryBusMiddleware first;

    public <T extends Query> InMemoryQueryBus(List<QueryHandler<T>> queryHandlers) {
        this.first = new ErrorManagementQueryBusMiddleware(new LoggerQueryBusMiddleware(new DispatcherQueryBusMiddleware(queryHandlers)));
    }

    @Override
    public QueryResponse dispatch(Query query) {
        Result<QueryResponse> result = this.first.dispatch(query);
        return result.getValue();
    }
}
