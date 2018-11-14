package com.epam.multithreading.entity;

import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.entity.currency.Euro;
import com.epam.multithreading.entity.currency.USDollar;
import com.epam.multithreading.observer.Observable;
import com.epam.multithreading.observer.Observer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exchange implements Observable<Observer>{


    private List<Observer> observers = new ArrayList<>();
    private ExchangeInfo exchangeInfo;
    private Euro euro = Euro.getInstance();
    private USDollar usDollar = USDollar.getInstance();

    private Map<CurrencyName, BigDecimal> currencyAmount = new HashMap<>();

    public Exchange(ExchangeInfo exchangeInfo) {
        this.exchangeInfo = exchangeInfo;

        currencyAmount.put(CurrencyName.EURO, BigDecimal.ZERO);
        currencyAmount.put(CurrencyName.U_S_DOLLAR, BigDecimal.ZERO);
        currencyAmount.put(CurrencyName.RUBLE, BigDecimal.ZERO);

    }

    public ExchangeInfo getExchangeInfo() {
        return exchangeInfo;
    }

    public Euro getEuro() {
        return euro;
    }

    public USDollar getUsDollar() {
        return usDollar;
    }

    public void setCurrencyAmount(Map<CurrencyName, BigDecimal> currencyAmount) {
        this.currencyAmount = currencyAmount;
        notifyObservers();
    }

    public void setCurrencyAmount(CurrencyName currencyName, BigDecimal amount) {
        this.currencyAmount.put(currencyName, amount);
        notifyObservers();
    }

    public Map<CurrencyName, BigDecimal> getCurrencyAmount() {
        return currencyAmount;
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
}
