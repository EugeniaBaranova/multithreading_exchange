package com.epam.multithreading.observer.commands.rate;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.currency.Euro;
import com.epam.multithreading.observer.commands.Command;

import java.math.BigDecimal;

public class GetEuroRateCommand implements Command {

    @Override
    public BigDecimal execute(Exchange exchange) {
        Euro euro = exchange.getEuro();
        return euro.getCurrencyRate();
    }
}
