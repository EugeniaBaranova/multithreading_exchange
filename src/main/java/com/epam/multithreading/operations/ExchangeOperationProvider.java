package com.epam.multithreading.operations;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.ExchangeParticipant;
import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.Command;
import com.epam.multithreading.observer.commands.CommandProvider;
import com.epam.multithreading.observer.commands.RateCommandProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExchangeOperationProvider {

    private Lock lock = new ReentrantLock();
    //TODO Do I need condition?
    private Condition condition = lock.newCondition();
    private RateCommandProvider rateCommandProvider = new RateCommandProvider();
    private CommandProvider commandProvider;
    private List<CurrencyName> currencyNames;
    private ExchangeParticipant exchangeParticipant;

    public ExchangeOperationProvider(CommandProvider commandProvider,
                                     List<CurrencyName> currencyNames,
                                     ExchangeParticipant participant) {
        this.commandProvider = commandProvider;
        this.currencyNames = currencyNames;
        this.exchangeParticipant = participant;
    }

    public boolean buyCurrency(CurrencyName currencyName, BigDecimal requiredAmount) {

        try {
            while (!lock.tryLock()) {
                condition.await();
            }
            if (currencyName != null && requiredAmount != null) {
                for (CurrencyName name : currencyNames) {

                    if (name == currencyName) {
                        Exchange exchange = exchangeParticipant.getExchange();

                        List<Command> commands = commandProvider.getCommand(currencyName);
                        Command command = commands.get(0);
                        BigDecimal exchangeCurrencyAmount = command.execute(exchange);

                        Command rateCommand = rateCommandProvider.getCommand(currencyName);
                        BigDecimal rate = rateCommand.execute(exchange);

                        //TODO Command
                        Map<CurrencyName, BigDecimal> currencyAmount = exchange.getCurrencyAmount();
                        BigDecimal availableRublesAmount = currencyAmount.get(CurrencyName.RUBLE);

                        BigDecimal neededRublesAmount = requiredAmount.multiply(rate);
                        if (neededRublesAmount.compareTo(availableRublesAmount) <= 0) {
                            if (requiredAmount.compareTo(exchangeCurrencyAmount) <= 0) {

                                updateParticipant(currencyName, requiredAmount, availableRublesAmount, neededRublesAmount);
                                updateExchange(currencyName, requiredAmount, exchangeCurrencyAmount, neededRublesAmount,
                                        exchange);
                            }
                        }
                    }
                }
            }
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean sellCurrency(CurrencyName currencyName, BigDecimal requiredAmount) {
        try {
            while (!lock.tryLock()) {
                condition.await();
            }
            if (currencyName != null && requiredAmount != null) {
                for (CurrencyName name : currencyNames) {

                    if (name == currencyName) {
                        Exchange exchange = exchangeParticipant.getExchange();

                        List<Command> commands = commandProvider.getCommand(currencyName);
                        Command command = commands.get(0);
                        BigDecimal exchangeCurrencyAmount = command.execute(exchange);

                        Command rateCommand = rateCommandProvider.getCommand(currencyName);
                        BigDecimal rate = rateCommand.execute(exchange);

                        //TODO Command
                        Map<CurrencyName, BigDecimal> currencyAmount = exchange.getCurrencyAmount();
                        BigDecimal availableRublesAmount = currencyAmount.get(CurrencyName.RUBLE);

                        BigDecimal neededRublesAmount = requiredAmount.multiply(rate);
                        if (neededRublesAmount.compareTo(availableRublesAmount) <= 0) {
                            if (requiredAmount.compareTo(exchangeCurrencyAmount) <= 0) {

                                /*updateParticipant(currencyName, requiredAmount, availableRublesAmount, neededRublesAmount);
                                updateExchange(currencyName, requiredAmount, exchangeCurrencyAmount, neededRublesAmount,
                                        exchange);*/
                            }
                        }
                    }
                }
            }
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    private void updateParticipant(CurrencyName currencyName,
                                   BigDecimal requiredAmount,
                                   BigDecimal availableRublesAmount,
                                   BigDecimal neededRublesAmount) {
        BigDecimal newRublesAmount = availableRublesAmount.subtract(neededRublesAmount);
        exchangeParticipant.setCurrencyAmount(CurrencyName.RUBLE, newRublesAmount);

        Map<CurrencyName, BigDecimal> currencyAmount = exchangeParticipant.getCurrencyAmount();
        BigDecimal participantCurrencyAmount = currencyAmount.get(currencyName);
        BigDecimal newCurrencyAmount = participantCurrencyAmount.add(requiredAmount);
        exchangeParticipant.setCurrencyAmount(currencyName, newCurrencyAmount);
    }

    private void updateExchange(CurrencyName currencyName,
                                BigDecimal requiredAmount,
                                BigDecimal exchangeCurrencyAmount,
                                BigDecimal neededRublesAmount,
                                Exchange exchange) {
        //TODO Command
        Map<CurrencyName, BigDecimal> currencyAmount = exchange.getCurrencyAmount();
        BigDecimal rubleAmount = currencyAmount.get(CurrencyName.RUBLE);
        BigDecimal newRublesAmount = rubleAmount.add(neededRublesAmount);
        exchange.setCurrencyAmount(CurrencyName.RUBLE, newRublesAmount);

        BigDecimal newCurrencyAmount = exchangeCurrencyAmount.subtract(requiredAmount);
        exchange.setCurrencyAmount(currencyName, newCurrencyAmount);
    }
}
