package com.example.demo.queries;

import com.example.demo.ports.OrderRepository;
import ddd.monad.Result;
import ddd.query.QueryHandler;
import ddd.query.QueryResponse;
import org.springframework.stereotype.Service;

@Service
public class GetOrdersQueryHandler implements QueryHandler<GetOrdersQuery> {

    private final OrderRepository orderRepository;

    public GetOrdersQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Result<QueryResponse> handle(GetOrdersQuery query) {
        return Result.ok(new GetOrdersQueryResponse(this.orderRepository.getOrders()));
    }

    @Override
    public Class<GetOrdersQuery> listenTo() {
        return GetOrdersQuery.class;
    }
}
