package com.epam.multithreading.observer.commands.currentInfo;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.ExchangeInfo;
import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.Command;

import java.math.BigDecimal;
import java.util.Map;

public class GetCurrentEuroAmountCommand implements Command {

    @Override
    public BigDecimal execute(Exchange exchange) {
        ExchangeInfo exchangeInfo = exchange.getExchangeInfo();
        Map<CurrencyName, BigDecimal> currentCurrencyAmount = exchangeInfo.getCurrentCurrencyAmount();
        return currentCurrencyAmount.get(CurrencyName.EURO);
    }
}
