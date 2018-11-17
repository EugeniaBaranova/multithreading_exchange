package com.epam.multithreading.observer.commands;

import com.epam.multithreading.entity.currency.CurrencyName;
import com.epam.multithreading.observer.commands.currentInfo.GetCurrentDollarAmountCommand;
import com.epam.multithreading.observer.commands.currentInfo.GetCurrentEuroAmountCommand;
import com.epam.multithreading.observer.commands.newInfo.GetNewDollarAmountCommand;
import com.epam.multithreading.observer.commands.newInfo.GetNewEuroAmountCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommandProvider {

    List<Command> getCommand(CurrencyName commandName);
}
