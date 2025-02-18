package com.example.demo.events;

import ddd.event.EventHandler;
import org.springframework.stereotype.Service;

@Service
public class DisplayTotoOnOrderInitialized implements EventHandler<OrderInitialized> {
    @Override
    public void handle(OrderInitialized event) {
        System.out.println("toto");
    }

    @Override
    public Class<OrderInitialized> listenTo() {
        return OrderInitialized.class;
    }
}
