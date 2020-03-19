package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    //private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.info_en", new Locale("EN"));
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"info_en");
    @Override
    public void execute() {
        Collection<CurrencyManipulator> manipulatorsList = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulatorsList.isEmpty()) {
            //ConsoleHelper.writeMessage("No money available.");
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
        for (CurrencyManipulator manipulator : manipulatorsList) {
            if (manipulator.hasMoney()) {
                //ConsoleHelper.writeMessage(manipulator.getCurrencyCode().toUpperCase() + " - " + manipulator.getTotalAmount());
                ConsoleHelper.writeMessage(res.getString("before")+manipulator.getCurrencyCode().toUpperCase() + " - " + manipulator.getTotalAmount());
            } else {
                //ConsoleHelper.writeMessage("No money available.");
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }
    }
}
