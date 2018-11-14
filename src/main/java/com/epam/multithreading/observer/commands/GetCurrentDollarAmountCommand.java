package com.epam.multithreading.observer.commands;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.ExchangeInfo;
import com.epam.multithreading.entity.currency.CurrencyName;

import java.math.BigDecimal;
import java.util.Map;

public class GetCurrentDollarAmountCommand implements Command {

    @Override
    public BigDecimal execute(Exchange exchange) {
        ExchangeInfo exchangeInfo = exchange.getExchangeInfo();
        Map<CurrencyName, BigDecimal> currentCurrencyAmount = exchangeInfo.getCurrentCurrencyAmount();
        return currentCurrencyAmount.get(CurrencyName.U_S_DOLLAR);
    }
}