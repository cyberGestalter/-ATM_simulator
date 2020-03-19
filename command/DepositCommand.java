package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] denominationAndCount = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        manipulator.addAmount(Integer.parseInt(denominationAndCount[0]), Integer.parseInt(denominationAndCount[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(denominationAndCount[0])*Integer.parseInt(denominationAndCount[1]), currencyCode));
    }
}
