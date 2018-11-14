package com.epam.multithreading.observer;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.ExchangeInfo;
import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.CommandProvider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeObserver implements Observer {
    private CurrencyRateCounter rateCounter;
    private Exchange exchange;

    public ExchangeObserver(Exchange exchange, List<CurrencyName> currencyNames) {
        this.exchange = exchange;
        this.exchange
                .attach(this);
        rateCounter = new CurrencyRateCounter(new CommandProvider(), currencyNames);
    }

    @Override
    public void update() {

        rateCounter.countNewRates(exchange);

        Map<CurrencyName, BigDecimal> currencyAmount = exchange.getCurrencyAmount();

        BigDecimal newEurosAmount = currencyAmount.get(CurrencyName.EURO);
        BigDecimal newDollarsAmount = currencyAmount.get(CurrencyName.U_S_DOLLAR);
        BigDecimal newRublesAmount = currencyAmount.get(CurrencyName.RUBLE);

        ExchangeInfo exchangeInfo = exchange.getExchangeInfo();

        Map<CurrencyName, BigDecimal> newCurrencyAmount = new HashMap<>();
        newCurrencyAmount.put(CurrencyName.EURO, newEurosAmount);
        newCurrencyAmount.put(CurrencyName.U_S_DOLLAR, newDollarsAmount);
        newCurrencyAmount.put(CurrencyName.RUBLE, newRublesAmount);

        exchangeInfo.setCurrentCurrencyAmount(newCurrencyAmount);
    }
}
