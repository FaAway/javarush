package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;
import java.util.ResourceBundle;

/**
 * Created by FarAway on 21.02.2016.
 */
public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".common");

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String result = "";
        try {
            result = reader.readLine();
            if (result.toLowerCase().equals("exit"))
                throw new InterruptOperationException();
        } catch (IOException e) {
            //do nothing
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        String currencyCode;
        do {
            writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();
            if (currencyCode.length() != 3)
                writeMessage(res.getString("invalid.data"));
            else break;
        } while (true);
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        String[] denominationAndAmmount;
        do {
            writeMessage(res.getString("choose.denomination.and.count.format"));
            try {
                denominationAndAmmount = readString().split(" ");
                if (denominationAndAmmount.length != 2) throw new NumberFormatException(res.getString("invalid.data"));
                int denomination = Integer.parseInt(denominationAndAmmount[0]); //check Integer
                int ammount = Integer.parseInt(denominationAndAmmount[1]); //check Integer
                if (denomination <= 0 || ammount <= 0) throw new NumberFormatException(res.getString("invalid.data"));
                break; //if reached this point  - It's ok!
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        } while (true);
        return denominationAndAmmount;
    }

    public static Operation askOperation() throws InterruptOperationException{
        /*Спросить у пользователя операцию.
        Если пользователь вводит 1, то выбирается команда INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
        Используйте метод, описанный в п .1.
        Обработай исключение - запроси данные об операции повторно.*/
        writeMessage(res.getString("choose.operation"));
        StringBuilder opsString = new StringBuilder();
        for (Operation o : Operation.values()) {
            if (o != Operation.LOGIN)
                opsString.append(String.format("%d - %s, ", o.ordinal(), res.getString("operation." + o.name())));
        }


        opsString.delete(opsString.length() - 2, opsString.length());
        writeMessage(opsString.toString());
        //--------------------------------------
        int opCode = 0;
        Operation operation;
        do {
            try {
                opCode = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(opCode);
                break; //If reached - It's ok. Goto return.
            } catch (IllegalFormatException e) {
                writeMessage(res.getString("invalid.data"));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        } while (true);

        return operation;
    }

    public static void byeMessage() {
        writeMessage(res.getString("the.end"));
    }
}
