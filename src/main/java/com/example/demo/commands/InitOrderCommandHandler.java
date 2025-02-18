package com.example.demo.commands;

import com.example.demo.domain.Order;
import com.example.demo.events.OrderInitialized;
import com.example.demo.ports.OrderRepository;
import ddd.command.CommandHandler;
import ddd.command.CommandResponse;
import ddd.domain.Id;
import ddd.monad.Result;
import org.springframework.stereotype.Service;

@Service
public class InitOrderCommandHandler implements CommandHandler<InitOrderCommand> {

    private final OrderRepository orderRepository;

    public InitOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Result<CommandResponse> handle(InitOrderCommand command) {
        Order order = Order.init(command.getDeliveryLocation(), "user-id");

        Result<Order> existingOrder = this.orderRepository.getById(order.id());

        if (!existingOrder.isError()) {
            return Result.error("ORDER_ALREADY_EXISTS");
        }

        Result<Id> savedId = this.orderRepository.save(order);
        return savedId.flatMap(id -> Result.ok(new InitOrderCommandResponse(id, new OrderInitialized(id))));
    }

    @Override
    public Class<InitOrderCommand> listenTo() {
        return InitOrderCommand.class;
    }
}
