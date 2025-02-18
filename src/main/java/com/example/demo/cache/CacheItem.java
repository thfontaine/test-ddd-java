package com.example.demo.cache;

import lombok.Getter;

import java.time.Instant;

@Getter
public class CacheItem {
    private final Class aClass;
    private final Instant instant;
    private final String objectValue;

    public CacheItem(Class aClass, Instant instant, String objectValue) {
        this.aClass = aClass;
        this.instant = instant;
        this.objectValue = objectValue;
    }
}
