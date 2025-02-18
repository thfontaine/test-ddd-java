package com.example.demo.controllers;

import com.example.demo.commands.InitOrderCommand;
import com.example.demo.commands.InitOrderCommandResponse;
import com.example.demo.domain.DeliveryLocation;
import com.example.demo.queries.GetOrdersQuery;
import com.example.demo.queries.GetOrdersQueryResponse;
import ddd.command.CommandBus;
import ddd.domain.Id;
import ddd.query.QueryBus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitOrderController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public InitOrderController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping("/order")
    void initOrder() {
        DeliveryLocation deliveryLocation = new DeliveryLocation(Id.init(), "name");
        InitOrderCommand initOrderCommand = new InitOrderCommand(deliveryLocation);

        InitOrderCommandResponse commandResponse = (InitOrderCommandResponse) this.commandBus.dispatch(initOrderCommand);

        System.out.println(commandResponse.getId());
    }

    @GetMapping("/orders")
    void getOrders() {
        GetOrdersQuery getOrdersQuery = new GetOrdersQuery();

        GetOrdersQueryResponse getOrdersQueryResponse = (GetOrdersQueryResponse) this.queryBus.dispatch(getOrdersQuery);

        System.out.println(getOrdersQueryResponse.getOrders().toString());
    }
}
