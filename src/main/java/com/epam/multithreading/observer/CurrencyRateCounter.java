package com.epam.multithreading.observer;

import com.epam.multithreading.entity.*;
import com.epam.multithreading.entity.currency.Currency;
import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.entity.currency.Euro;
import com.epam.multithreading.entity.currency.USDollar;
import com.epam.multithreading.observer.commands.Command;
import com.epam.multithreading.observer.commands.currentInfo.CommandProvider;

import java.math.BigDecimal;
import java.util.*;

public class CurrencyRateCounter {

    private CommandProvider commandProvider;
    private List<CurrencyName> currencyNames;
    private Euro euro = Euro.getInstance();
    private USDollar usDollar = USDollar.getInstance();
    private Map<CurrencyName, Currency> currencies = new HashMap<>();
    {
        currencies.put(CurrencyName.EURO, euro);
        currencies.put(CurrencyName.U_S_DOLLAR, usDollar);
    }

    public CurrencyRateCounter(CommandProvider commandProvider, List<CurrencyName> currencyNames) {
        this.commandProvider = commandProvider;
        this.currencyNames = currencyNames;
    }

    void countNewRates (Exchange exchange){
        if (exchange != null) {

            for (CurrencyName currencyName : currencyNames) {
                Currency currency = currencies.get(currencyName);

                BigDecimal rate = currency.getCurrencyRate();
                BigDecimal delta = calculateDelta(exchange, currencyName);
                BigDecimal decimalSin = calculateDecimalSinusDelta(delta);

                BigDecimal newRate;
                if (delta.signum() < 0) {
                    newRate = rate.subtract(decimalSin);
                } else {
                    newRate = rate.add(decimalSin);
                }
                currency.setCurrencyRate(newRate);
            }
        }
    }

    private BigDecimal calculateDelta(Exchange exchange, CurrencyName currencyName) {
        List<Command> commands = commandProvider.getCommand(currencyName);
        Command firstCommand = commands.get(0);
        Command secondCommand = commands.get(1);
        BigDecimal previousAmount = firstCommand.execute(exchange);
        BigDecimal newAmount = secondCommand.execute(exchange);
        return previousAmount.subtract(newAmount);
    }

    private BigDecimal calculateDecimalSinusDelta(BigDecimal delta) {
        double deltaDouble = delta.doubleValue();
        double sinDelta = Math.sin(deltaDouble);
        double absSinDelta = Math.abs(sinDelta);
        return BigDecimal.valueOf(absSinDelta);
    }


}
