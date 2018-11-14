package com.epam.multithreading.operations;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.Command;
import com.epam.multithreading.observer.commands.CommandProvider;
import com.epam.multithreading.observer.commands.RateCommandProvider;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeOperations {

    private RateCommandProvider rateCommandProvider = new RateCommandProvider();
    private CommandProvider commandProvider;
    private List<CurrencyName> currencyNames;
    private Exchange exchange;

    public ExchangeOperations(CommandProvider commandProvider, List<CurrencyName> currencyNames, Exchange exchange) {
        this.commandProvider = commandProvider;
        this.currencyNames = currencyNames;
        this.exchange = exchange;
    }

    public boolean buyCurrency(CurrencyName currencyName, BigDecimal requiredAmount, BigDecimal availableRublesAmount){
        if(currencyName != null && requiredAmount != null && availableRublesAmount != null){
            for (CurrencyName name : currencyNames) {
                //TODO equals?
                if(name == currencyName){
                    List<Command> commands = commandProvider.getCommand(currencyName);
                    Command command = commands.get(0);
                    BigDecimal exchangeCurrencyAmount = command.execute(exchange);

                    Command rateCommand = rateCommandProvider.getCommand(currencyName);
                    BigDecimal rate = rateCommand.execute(exchange);

                    BigDecimal neededRublesAmount = requiredAmount.divide(rate, BigDecimal.ROUND_DOWN);
                    if(neededRublesAmount.compareTo(availableRublesAmount) <= 0){
                        if(requiredAmount.compareTo(exchangeCurrencyAmount) <= 0){


                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean sellCurrency(){
        return false;
    }

}
