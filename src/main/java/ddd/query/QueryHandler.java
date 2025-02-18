package ddd.query;

import ddd.monad.Result;

public interface QueryHandler<T extends Query> {
    Result<QueryResponse> handle(T query);
    Class<T> listenTo();
}
