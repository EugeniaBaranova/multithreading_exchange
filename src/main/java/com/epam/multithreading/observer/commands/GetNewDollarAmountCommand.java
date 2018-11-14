package com.epam.multithreading.observer.commands;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.currency.CurrencyName;

import java.math.BigDecimal;
import java.util.Map;

public class GetNewDollarAmountCommand implements Command {
    @Override
    public BigDecimal execute(Exchange exchange) {
        Map<CurrencyName, BigDecimal> currencyAmount = exchange.getCurrencyAmount();
        return currencyAmount.get(CurrencyName.U_S_DOLLAR);
    }
}
