package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by FarAway on 11.03.2016.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread {
        //15.1.
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        //16.1.
        protected void clientHandshake() throws IOException,ClassNotFoundException {
            boolean accepted = false;
            //16.1.1.
            while (!accepted) {
                Message message = connection.receive();
                switch (message.getType()) {
                    //16.1.2.
                    case NAME_REQUEST:
                        String clientName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, clientName));
                        break;
                    //16.1.3.
                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;
                    //16.1.4.
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }
        //17
        public void run() {
            //17.1.
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try {
                //17.2.
                Socket socket = new Socket(serverAddress, serverPort);
                //17.3.
                Client.this.connection = new Connection(socket);
                //17.4.
                clientHandshake();
                //17.5.
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }

        //16.2.
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            //16.2.6.
            while (!Thread.currentThread().isInterrupted()) { //but interrupted checking for daemon isn't necessary
                //16.2.1.
                Message message = connection.receive();
                switch (message.getType()) {
                    //16.2.2.
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;
                    //16.2.3.
                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;
                    //16.2.4.
                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;
                    //16.2.5.
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка при отправке сообщения. Соединение будет закрыто.");
            clientConnected = false;
        }
    }

    public void run() {
        //14.1.1.
        SocketThread socketThread = getSocketThread();
        //14.1.2.
        socketThread.setDaemon(true);
        //14.1.3.
        socketThread.start();
        //14.1.4.
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Выход из программы по ошибке.");
                return;
            }
        }
        //14.1.5.
        if (clientConnected)
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        //14.1.6
        while (clientConnected) {
            String text = ConsoleHelper.readString();
            if (text.toLowerCase().equals("exit")) break;
            //14.1.7.
            if (shouldSentTextFromConsole())
                sendTextMessage(text);
        }
    }

    //14.2.
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
