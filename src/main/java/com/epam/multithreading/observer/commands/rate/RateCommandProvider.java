package com.epam.multithreading.observer.commands.rate;

import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.Command;
import com.epam.multithreading.observer.commands.CommandProvider;

import java.util.HashMap;
import java.util.Map;

public class RateCommandProvider implements CommandProvider {

    private Map<CurrencyName, Command> commandMap = new HashMap<>();

    public RateCommandProvider() {
        commandMap.put(
                CurrencyName.EURO,
                new GetEuroRateCommand());
        commandMap.put(
                CurrencyName.U_S_DOLLAR,
                new GetDollarRateCommand());
    }

    public Command getCommand(CurrencyName commandName) {
        return commandMap.get(commandName);
    }

}
