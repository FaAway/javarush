package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FarAway on 22.02.2016.
 */
public class LoginCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".login");
    //FileInputStream fis;
    //private ResourceBundle validCreditCards;
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".verifiedCards");

    //private static final String cardNumber = "123456789012";
    //private static final String cardPin = "1234";
    @Override
    public void execute() throws InterruptOperationException {
        /*try {
            fis = new FileInputStream("com.javarush.test.level26.lesson15.big01.resources.verifiedCards");
            validCreditCards = new PropertyResourceBundle(fis);
            fis.close();
        } catch (IOException e) {e.printStackTrace();}*/

        /*
before=Logging in...
specify.data=Please specify your credit card number and pin code or type 'EXIT' for exiting.
success.format=Credit card [%s] is verified successfully!
not.verified.format=Credit card [%s] is not verified.
try.again.or.exit=Please try again or type 'EXIT' for urgent exiting
try.again.with.details=Please specify valid credit card number - 12 digits, pin code - 4 digits.
         */

        Matcher m1;
        Matcher m2;
        String cardNumberToCheck;
        boolean login = false;
        do {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.data"));

            cardNumberToCheck = ConsoleHelper.readString();
            String cardPinToCheck = ConsoleHelper.readString();
            Pattern p1 = Pattern.compile("^\\d{12}$");
            m1 = p1.matcher(cardNumberToCheck);
            Pattern p2 = Pattern.compile("^\\d{4}$");
            m2 = p2.matcher(cardPinToCheck);
            if (!m1.matches() || !m2.matches()) {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
            try {
                login = validCreditCards.getString(cardNumberToCheck).equals(cardPinToCheck);

            } catch (MissingResourceException e) {}
            if (!login) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumberToCheck));
                continue;
            }
        } while (!login);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumberToCheck));
    }
}
