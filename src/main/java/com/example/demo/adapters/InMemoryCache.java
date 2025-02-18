package com.example.demo.adapters;

import com.example.demo.cache.CacheItem;
import com.example.demo.ports.Cache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ddd.monad.Result;
import ddd.query.QueryResponse;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryCache implements Cache {
    private final List<CacheItem> items;
    private final ObjectMapper objectMapper;

    public InMemoryCache() {
        this.items = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Result<QueryResponse> add(Result<QueryResponse> item) {
        QueryResponse itemValue = item.getValue();
        Instant now = Instant.now();
        Class aClass = itemValue.getClass();

        try {
            String objectValue = this.objectMapper.writeValueAsString(itemValue);
            CacheItem cacheItem = new CacheItem(aClass, now, objectValue);
            this.items.add(cacheItem);
        } catch (JsonProcessingException e) {
            return Result.error("UNABLE_TO_ADD_TO_CACHE");
        }

        return Result.ok(itemValue);
    }

    @Override
    public Result get(Instant instant, Class aClass) {
        Optional<CacheItem> optionalCacheItem = this.items.stream().filter(
                cacheItem -> aClass.equals(cacheItem.getAClass()) && instant.isBefore(cacheItem.getInstant().plus(Duration.of(30, ChronoUnit.SECONDS)))
        ).findFirst();
        System.out.println(optionalCacheItem);
        try {
            return optionalCacheItem.isEmpty() ? Result.error("UNABLE_TO_GET_FROM_CACHE") : Result.ok(this.objectMapper.readValue(optionalCacheItem.get().getObjectValue(), optionalCacheItem.get().getAClass()));
        } catch (JsonProcessingException e) {
            return Result.error("UNABLE_TO_GET_FROM_CACHE");
        }
    }
}
