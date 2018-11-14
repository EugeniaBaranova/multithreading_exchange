package com.epam.multithreading.observer.commands;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.currency.Euro;

import java.math.BigDecimal;

public class GetEuroRateCommand implements Command {

    @Override
    public BigDecimal execute(Exchange exchange) {
        Euro euro = exchange.getEuro();
        return euro.getCurrencyRate();
    }
}
