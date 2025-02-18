package com.example.demo.ports;

import ddd.monad.Result;
import ddd.query.QueryResponse;

import java.time.Instant;

public interface Cache {
    Result<QueryResponse> add(Result<QueryResponse> cacheItem);
    Result<QueryResponse> get(Instant instant, Class<QueryResponse> aClass);
}
