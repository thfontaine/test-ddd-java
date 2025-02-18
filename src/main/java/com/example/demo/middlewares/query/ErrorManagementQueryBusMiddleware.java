package com.example.demo.middlewares.query;

import com.example.demo.exceptions.ApplicationException;
import ddd.monad.Result;
import ddd.query.Query;
import ddd.query.QueryBusMiddleware;
import ddd.query.QueryResponse;

public class ErrorManagementQueryBusMiddleware implements QueryBusMiddleware {

    private QueryBusMiddleware next;

    public ErrorManagementQueryBusMiddleware(QueryBusMiddleware next) {
        this.next = next;
    }

    @Override
    public Result<QueryResponse> dispatch(Query query) {
        Result<QueryResponse> result = this.next.dispatch(query);

        if (result.isError()) {
            throw new ApplicationException(result.getError());
        }

        return result;
    }
}
