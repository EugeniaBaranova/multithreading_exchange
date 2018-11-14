package com.epam.multithreading.observer.commands;

import com.epam.multithreading.entity.Exchange;

import java.math.BigDecimal;

public interface Command {
    BigDecimal execute(Exchange exchange);
}
