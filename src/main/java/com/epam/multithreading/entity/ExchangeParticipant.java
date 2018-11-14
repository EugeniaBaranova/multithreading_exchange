package com.epam.multithreading.entity;

import com.epam.multithreading.entity.currency.CurrencyName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExchangeParticipant implements Callable<Object>{

    private Exchange exchange;

    private Map<CurrencyName, BigDecimal> currencyAmount = new HashMap<>();

    public ExchangeParticipant(Exchange exchange) {
        this.exchange = exchange;

        currencyAmount.put(CurrencyName.EURO, BigDecimal.ZERO);
        currencyAmount.put(CurrencyName.U_S_DOLLAR, BigDecimal.ZERO);
        currencyAmount.put(CurrencyName.RUBLE, BigDecimal.ZERO);
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setCurrencyAmount(Map<CurrencyName, BigDecimal> currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public Map<CurrencyName, BigDecimal> getCurrencyAmount() {
        return currencyAmount;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
