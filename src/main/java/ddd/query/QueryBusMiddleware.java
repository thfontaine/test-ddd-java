package ddd.query;

import ddd.monad.Result;

public interface QueryBusMiddleware {
    Result<QueryResponse> dispatch (Query query);
}
