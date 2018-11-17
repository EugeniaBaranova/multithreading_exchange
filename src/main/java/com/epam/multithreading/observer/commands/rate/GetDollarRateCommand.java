package com.epam.multithreading.observer.commands.rate;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.currency.USDollar;
import com.epam.multithreading.observer.commands.Command;

import java.math.BigDecimal;

public class GetDollarRateCommand implements Command {

    @Override
    public BigDecimal execute(Exchange exchange) {
        USDollar usDollar = exchange.getUsDollar();
        return usDollar.getCurrencyRate();
    }
}
