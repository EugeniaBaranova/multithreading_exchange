package com.epam.multithreading.observer.commands.newInfo;

import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.Command;
import com.epam.multithreading.observer.commands.currentInfo.GetCurrentDollarAmountCommand;
import com.epam.multithreading.observer.commands.currentInfo.GetCurrentEuroAmountCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandProvider {

    private Map<CurrencyName, List<Command>> commandMap = new HashMap<>();

    public CommandProvider() {
        commandMap.put(
                CurrencyName.EURO,
                Arrays.asList(new GetCurrentEuroAmountCommand(),
                        new GetNewEuroAmountCommand()));
        commandMap.put(
                CurrencyName.U_S_DOLLAR,
                Arrays.asList(new GetCurrentDollarAmountCommand(),
                        new GetNewDollarAmountCommand()));
    }

    public List<Command> getCommand(CurrencyName commandName){
        return commandMap.get(commandName);
    }
}
