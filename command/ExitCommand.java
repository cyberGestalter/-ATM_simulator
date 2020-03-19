package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.Objects;
import java.util.ResourceBundle;

class ExitCommand implements Command {
    //private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.exit_en", new Locale("EN"));
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"exit_en");
    @Override
    public void execute() throws InterruptOperationException {

        /*1.1. Спросить, действительно ли пользователь хочет выйти - варианты <y,n>.
        1.2. Если пользователь подтвердит свои намерения, то попрощаться с ним.
        1.3. Если пользователь не подтвердит свои намерения, то не прощаться с ним, а просто выйти.*/

        //ConsoleHelper.writeMessage("Вы действительно хотите выйти? (y - Yes; n - No)");
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        switch (Objects.requireNonNull(ConsoleHelper.readString())) {
            case "y" : //ConsoleHelper.writeMessage("Прощай, сука. Спасибо, что пользуешься нашими услугами.");
                        ConsoleHelper.writeMessage(res.getString("thank.message"));
                break;
            case "n": return;
                //break;
            //default: throw new InterruptOperationException();
        }
    }
}
