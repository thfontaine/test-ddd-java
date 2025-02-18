package com.example.demo.queries;

import com.example.demo.domain.Order;
import ddd.query.QueryResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOrdersQueryResponse extends QueryResponse {
    private final List<Order> orders;

    public GetOrdersQueryResponse(List<Order> orders) {
        this.orders = orders;
    }
}
