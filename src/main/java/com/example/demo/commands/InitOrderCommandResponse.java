package com.example.demo.commands;

import ddd.command.CommandResponse;
import ddd.domain.Id;
import ddd.event.DomainEvent;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class InitOrderCommandResponse extends CommandResponse {
    private final Id id;

    public InitOrderCommandResponse(Id id, DomainEvent ...events) {
        super(Arrays.stream(events).toList());
        this.id = id;
    }
}
