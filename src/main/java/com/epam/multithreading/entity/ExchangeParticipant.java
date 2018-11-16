package com.epam.multithreading.entity;

import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.CommandProvider;
import com.epam.multithreading.operations.ExchangeOperationProvider;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExchangeParticipant implements Callable<Object> {

    private CommandProvider commandProvider = new CommandProvider();
    private Exchange exchange;
    private Map<CurrencyName, BigDecimal> currencyAmount = new HashMap<>();
    private CurrencyName currencyToBuy;
    private CurrencyName currencyToSell;
    private BigDecimal amountToBuy;
    private BigDecimal amountToSell;
    private ExchangeOperationProvider operationProvider;


    public ExchangeParticipant(Exchange exchange) {
        this.exchange = exchange;

        currencyAmount.put(CurrencyName.EURO, BigDecimal.ZERO);
        currencyAmount.put(CurrencyName.U_S_DOLLAR, BigDecimal.ZERO);
        currencyAmount.put(CurrencyName.RUBLE, BigDecimal.ZERO);

        operationProvider = new ExchangeOperationProvider(
                commandProvider,
                Arrays.asList(CurrencyName.EURO, CurrencyName.U_S_DOLLAR, CurrencyName.RUBLE),
                this);
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setCurrencyAmount(Map<CurrencyName, BigDecimal> currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public void setCurrencyAmount(CurrencyName currencyName, BigDecimal amount) {
        this.currencyAmount.put(currencyName, amount);
    }

    public Map<CurrencyName, BigDecimal> getCurrencyAmount() {
        return currencyAmount;
    }

    @Override
    public Map<CurrencyName, BigDecimal> call() throws Exception {
        operationProvider.buyCurrency(currencyToBuy, amountToBuy);
        operationProvider.sellCurrency(currencyToSell, amountToSell);
        return currencyAmount;
    }
}
