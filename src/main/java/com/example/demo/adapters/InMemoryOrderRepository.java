package com.example.demo.adapters;

import com.example.demo.domain.Order;
import com.example.demo.ports.OrderRepository;
import ddd.domain.Id;
import ddd.monad.Result;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<Id, Order> orders = new HashMap<>();

    @Override
    public Result<Id> save(Order order) {
        this.orders.put(order.id(), order);
        return Result.ok(order.id());
    }

    @Override
    public Result<Order> getById(Id id) {
        Order order = this.orders.get(id);

        if (order == null) {
            return Result.error("ORDER_NOT_FOUND");
        }

        return Result.ok(order);
    }

    @Override
    public List<Order> getOrders() {
        return this.orders.values().stream().toList();
    }
}
