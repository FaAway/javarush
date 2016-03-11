package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FarAway on 12.03.2016.
 */

//18.1.
public class BotClient extends Client{
    private static final String WELCOME_TEXT = "Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.";

    private static volatile Set<String> botNames = new HashSet<>();
    //18.2.
    public class BotSocketThread extends SocketThread {

        //19.1.
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            sendTextMessage(WELCOME_TEXT);
            super.clientMainLoop();
        }

        //19.2.
        @Override
        protected void processIncomingMessage(String message) {
            //19.2.1.
            ConsoleHelper.writeMessage(message);
            //19.2.2.
            String[] messageParts = message.split(": ");
            if (messageParts.length == 2) {
                String messageAuthor = messageParts[0];
                String messageText = messageParts[1].toLowerCase();
                String dateTimeformat = null;
                switch (messageText) {
                    case "дата":
                        dateTimeformat = "d.MM.YYYY";
                        break;
                    case "день":
                        dateTimeformat = "d";
                        break;
                    case "месяц":
                        dateTimeformat = "MMMM";
                        break;
                    case "год":
                        dateTimeformat = "YYYY";
                        break;
                    case "время":
                        dateTimeformat = "H:mm:ss";
                        break;
                    case "час":
                        dateTimeformat = "H";
                        break;
                    case "минуты":
                        dateTimeformat = "m";
                        break;
                    case "секунды":
                        dateTimeformat = "s";
                        break;
                }
                if (dateTimeformat != null) {
                    String reply = String.format("Информация для %s: %s",
                            messageAuthor,
                            new SimpleDateFormat(dateTimeformat).format(Calendar.getInstance().getTime())
                    );
                    sendTextMessage(reply);
                }
            }
        }
    }

    //18.3.
    //18.3.1.
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    //18.3.2.
    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    //18.3.3.
    @Override
    protected String getUserName() {
        String botName;
        if (botNames.size() >= 100) throw new RuntimeException("Число ботов превысило допустимый предел");
        do {
            botName = String.format("date_bot_%02d", new Random().nextInt(100));
        } while (botNames.contains(botName));
        botNames.add(botName);
        return botName;
    }

    //18.4.
    public static void main(String[] args) {
        new BotClient().run();
    }
}
