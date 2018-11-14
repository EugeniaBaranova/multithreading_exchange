package com.epam.multithreading.entity;

import com.epam.multithreading.entity.currency.CurrencyName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExchangeInfo {


    private Map<CurrencyName, BigDecimal> currentCurrencyAmount = new HashMap<>();

    public ExchangeInfo() {
        currentCurrencyAmount.put(CurrencyName.EURO, BigDecimal.ZERO);
        currentCurrencyAmount.put(CurrencyName.U_S_DOLLAR, BigDecimal.ZERO);
        currentCurrencyAmount.put(CurrencyName.RUBLE, BigDecimal.ZERO);
    }

    public void setCurrentCurrencyAmount(Map<CurrencyName, BigDecimal> currencyAmount) {
        this.currentCurrencyAmount = currencyAmount;
    }

    public Map<CurrencyName, BigDecimal> getCurrentCurrencyAmount() {
        return currentCurrencyAmount;
    }

}
