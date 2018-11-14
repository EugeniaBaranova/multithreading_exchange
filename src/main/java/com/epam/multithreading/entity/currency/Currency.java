package com.epam.multithreading.entity.currency;

import java.math.BigDecimal;

public abstract class Currency {

    private BigDecimal currencyRate;

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }
}
