package com.example.demo.commands;

import com.example.demo.domain.DeliveryLocation;
import ddd.command.Command;
import lombok.Getter;

@Getter
public class InitOrderCommand implements Command {
    private final DeliveryLocation deliveryLocation;

    public InitOrderCommand(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
}
