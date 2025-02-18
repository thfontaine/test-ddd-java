package com.example.demo.ports;

import com.example.demo.domain.Order;
import ddd.domain.Id;
import ddd.monad.Result;

import java.util.List;

public interface OrderRepository {
    Result<Id> save(Order order);
    Result<Order> getById(Id id);
    List<Order> getOrders();
}
