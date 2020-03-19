package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    //public String cardNumber = "123456789012";
    //public int pinCode = 1234;

    //private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.verifiedCards", new Locale("EN"));
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    //private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.login_en", new Locale("EN"));
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"login_en");
    @Override
    public void execute() throws InterruptOperationException {
        /*ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            //ConsoleHelper.writeMessage("Введите номер карты и пин:");
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String userCard = null;
            int userPin = 0;
            try {
                userCard = ConsoleHelper.readString();
                userPin = Integer.parseInt(ConsoleHelper.readString());
                if (userCard != null && userPin != 0) {
                    if (validCreditCards.containsKey(userCard)) {
                        int pinCode = Integer.parseInt(validCreditCards.getString(userCard));
                        if (pinCode == userPin) {
                            //ConsoleHelper.writeMessage("Верификация прошла успешно.");
                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), userCard));
                            return;
                        }
                    }
                    //ConsoleHelper.writeMessage("Номер карты и пин-код не подходят. Попробуйте еще раз.");
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCard));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                    //ConsoleHelper.readString();
                }
            } catch (IllegalArgumentException e) {
                //ConsoleHelper.writeMessage("Номер карты и пин-код имеют неверный формат.");
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                //ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
            //ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }*/
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true)
        {
            //ConsoleHelper.writeMessage("Введите номер карты и пин:");
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String userCard = ConsoleHelper.readString();
            String userPin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(userCard))
            {
                if (validCreditCards.getString(userCard).equals(userPin))
                    //ConsoleHelper.writeMessage("Верификация прошла успешно.");
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), userCard));
                else
                {
                    //ConsoleHelper.writeMessage("Номер карты и пин-код не подходят. Попробуйте еще раз.");
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCard));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            }
            else
            {
                //ConsoleHelper.writeMessage("Номер карты и пин-код имеют неверный формат.");
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCard));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            break;
        }
    }
}
