package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by FarAway on 21.02.2016.
 */

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".exit");
    @Override
    public void execute() throws InterruptOperationException {
        String commandAck;
            ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
            commandAck = ConsoleHelper.readString().toLowerCase();
            if (commandAck.equals(res.getString("yes")))
                ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
