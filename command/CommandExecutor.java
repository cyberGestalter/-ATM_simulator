package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.Operation;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;
import java.util.HashMap;
import java.util.Map;

//Класс для взаимодействия со всеми командами
public class CommandExecutor {
    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    static {
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
    }

    private CommandExecutor() {
    }

    public final static void execute(Operation operation) throws InterruptOperationException, NotEnoughMoneyException {
        allKnownCommandsMap.get(operation).execute();
    }
}
