package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

//Снятие денег
class WithdrawCommand implements Command {

    //private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.withdraw_en", new Locale("EN"));
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        /*String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true) {
            ConsoleHelper.writeMessage("Введите сумму для вывода:");
            int withdrawSumm = 0;
            while (true) {
                String withdrawSummString = ConsoleHelper.readString();
                try {
                    withdrawSumm = Integer.parseInt(withdrawSummString);
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage("Неверный формат ввода. Введите число");
                    continue;
                }
                if (manipulator.isAmountAvailable(withdrawSumm)) {
                    //Множество выданных банкнот
                    Map <Integer, Integer> residueBalance = manipulator.withdrawAmount(withdrawSumm);

                    List<Integer> residueBalanceKeys = new ArrayList<>(residueBalance.keySet());
                    Collections.sort(residueBalanceKeys);
                    for (Integer key : residueBalanceKeys) {
                        ConsoleHelper.writeMessage("\t" + key + " - " + residueBalance.get(key));
                    }
                    ConsoleHelper.writeMessage("Транзакция проведена успешно");
                    break;
                } else {
                    ConsoleHelper.writeMessage("На счету недостаточно средств. Введите меньшую сумму для вывода");
                }
            }
        }*/

        //CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        boolean isAmAv = false;
        boolean isOk = true;
        int summ = 0;
        TreeMap<Integer, Integer> resultMap = new TreeMap<>(Comparator.reverseOrder());

        do {
            //ConsoleHelper.writeMessage("Пожалуйста, введите сумму: ");

            ConsoleHelper.writeMessage(res.getString("specify.amount"));

            String str = ConsoleHelper.readString();
            try{
                summ = Integer.parseInt(str);
                if(str.matches("[0-9]+")){
                   isAmAv = (currencyManipulator.isAmountAvailable(summ));
                }

                 resultMap.putAll(currencyManipulator.withdrawAmount(summ));
                for(Map.Entry<Integer, Integer> entry : resultMap.entrySet()){
                    System.out.println("\t"+entry.getKey()+" - "+entry.getValue());
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), entry.getKey()*entry.getValue(), currencyCode));
                }
                //ConsoleHelper.writeMessage("Транзакция завершена успешно!");

                isOk = false;
                break;
            }catch (NotEnoughMoneyException | NumberFormatException e){
                //ConsoleHelper.writeMessage("Не удается набрать заказанную сумму( Попробуйте сумму кратную имеющимся номиналам!");
                ConsoleHelper.writeMessage(res.getString("not.enough.money") + " or " + res.getString("exact.amount.not.available"));
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        } while (isAmAv && isOk);
    }
}