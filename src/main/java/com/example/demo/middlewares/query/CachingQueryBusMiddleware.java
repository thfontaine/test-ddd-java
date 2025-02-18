package com.example.demo.middlewares.query;

import com.example.demo.ports.Cache;
import ddd.monad.Result;
import ddd.query.Query;
import ddd.query.QueryBusMiddleware;
import ddd.query.QueryResponse;

import java.time.Instant;

public class CachingQueryBusMiddleware implements QueryBusMiddleware {

    private final QueryBusMiddleware next;
    private final Cache cache;

    public CachingQueryBusMiddleware(Cache cache, QueryBusMiddleware next) {
        this.next = next;
        this.cache = cache;
    }


    @Override
    public Result<QueryResponse> dispatch(Query query) {
        Instant now = Instant.now();
        Result<QueryResponse> resultFromCache = this.cache.get(now, query.hasResponse());

        if (!resultFromCache.isError()) {
            System.out.println("USING_CACHED_VALUE");
            return resultFromCache;
        }

        Result<QueryResponse> result = this.next.dispatch(query);
        this.cache.add(result);

        return result;
    }
}
