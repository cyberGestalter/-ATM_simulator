package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Locale;

import static com.javarush.task.task26.task2613.command.CommandExecutor.execute;

//Главный класс программы
public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() +".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            execute(Operation.LOGIN);
            Operation askOperation;
            do {
                askOperation = ConsoleHelper.askOperation();

                    execute(askOperation);

            } while (!Operation.EXIT.equals(askOperation));
        } catch (InterruptOperationException | NotEnoughMoneyException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
/*Locale.setDefault(Locale.ENGLISH);
        Operation operation;
        try {
            do {
                operation = ConsoleHelper.askOperation();

                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        }catch (InterruptOperationException e){
            ConsoleHelper.writeMessage("Bay Bay!!!");
        }*/