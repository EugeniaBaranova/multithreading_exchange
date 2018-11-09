package com.epam.multithreading.entity;

public abstract class Currency {

    private Integer currencyRate;

    public Integer getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Integer currencyRate) {
        this.currencyRate = currencyRate;
    }
}
