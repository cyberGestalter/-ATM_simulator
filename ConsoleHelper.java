package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.common_en", new Locale("EN"));

    private static String end = res.getString("the.end");

    public static void printExitMessage() {
        writeMessage(end);
    }
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String result = null;
        try {
            result = bis.readLine();
            if (result.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
            //return bis.readLine();
        } catch (IOException e) {

        }
        return result;
    }
    //Предлагает пользователю ввести код валюты
    public static String askCurrencyCode() throws InterruptOperationException{
        String currencyCode = "";
        while (true) {
            //writeMessage("Введите код валюты: ");
            writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();
            if (currencyCode != null) {
                if (currencyCode.length() == 3) break;
            }
        }
        return currencyCode.toUpperCase();
    }

    //считывает номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        String[] banknotsNominalAndQuantity = new String[2];
        int indexOfSpace;
        String banknotsNominal;
        String banknotsQuantity;
        while (true) {
            //writeMessage("Введите номинал и количество банкнот через пробел: ");
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String nominalAndQuantity = readString();
            if (nominalAndQuantity != null) {
                if (nominalAndQuantity.contains(" ")) {
                    indexOfSpace = nominalAndQuantity.indexOf(" ");
                    banknotsNominal = nominalAndQuantity.substring(0, indexOfSpace);
                    banknotsQuantity = nominalAndQuantity.substring(indexOfSpace + 1);
                    try {
                        int denomination = Integer.parseInt(banknotsNominal);
                        int count = Integer.parseInt(banknotsQuantity);
                        if (denomination > 0 && count > 0) {
                            banknotsNominalAndQuantity[0] = banknotsNominal;
                            banknotsNominalAndQuantity[1] = banknotsQuantity;
                            break;
                        }
                    } catch (Exception e) {
                        //ConsoleHelper.writeMessage("Введена нечисловая информация, пожалуйста, введите номинал и количество банкнот заново");
                        writeMessage(res.getString("invalid.data"));
                    }
                }
            }
        }
        return banknotsNominalAndQuantity;
    }

    public static Operation askOperation() throws InterruptOperationException{
        Operation result = null;
        while (true) {
            //writeMessage("Какую операцию необходимо выполнить? (1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT)");
            writeMessage(res.getString("choose.operation"));
            writeMessage("1 - "+res.getString("operation.INFO")+", "+"2 - "+res.getString("operation.DEPOSIT")+", "+"3 - "+res.getString("operation.WITHDRAW")+", "+"4 - "+res.getString("operation.EXIT"));
            String operation = readString();
            switch (operation) {
                case "0": throw new IllegalArgumentException();//writeMessage("Введена неверная информация. Пожалуйста, введите число из предложенных");
                case "1":
                case "2":
                case "3":
                case "4":   result = Operation.getAllowableOperationByOrdinal(Integer.parseInt(Objects.requireNonNull(operation)));
                            break;
                default: //writeMessage("Введена неверная информация. Пожалуйста, введите число из предложенных");
                        writeMessage(res.getString("invalid.data"));
                        continue;
            }
            break;
        }
        return result;
    }
}
