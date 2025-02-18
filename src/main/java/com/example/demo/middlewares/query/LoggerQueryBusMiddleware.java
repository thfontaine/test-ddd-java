package com.example.demo.middlewares.query;

import ddd.monad.Result;
import ddd.query.Query;
import ddd.query.QueryBusMiddleware;
import ddd.query.QueryResponse;

import java.time.Instant;

public class LoggerQueryBusMiddleware implements QueryBusMiddleware {

    private final QueryBusMiddleware next;

    public LoggerQueryBusMiddleware(QueryBusMiddleware next) {
        this.next = next;
    }

    @Override
    public Result<QueryResponse> dispatch(Query query) {
        long startTime = Instant.now().toEpochMilli();
        Result<QueryResponse> queryResponse = this.next.dispatch(query);
        long endTime = Instant.now().toEpochMilli();

        System.out.println("Query took " + (endTime - startTime) + " ms");
        return queryResponse;
    }
}
