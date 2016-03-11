package com.javarush.test.level30.lesson15.big01.client;

/**
 * Created by FarAway on 12.03.2016.
 */

//21.1.
public class ClientGuiController extends Client{

    private ClientGuiModel model;
    private ClientGuiView view;

    public ClientGuiController() {
        //21.2.
        model = new ClientGuiModel();
        //21.3.
        view = new ClientGuiView(this);
    }

    //21.4.
    public class GuiSocketThread extends SocketThread {
        //21.4.1.
        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }

        //21.4.2.
        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }

        //21.4.3.
        @Override
        protected void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }

        //21.4.4.
        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

    //21.5.1.
    @Override
    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    //21.5.2.
    @Override
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.run();
    }

    //21.5.3.
    @Override
    protected String getServerAddress() {
        return view.getServerAddress();
    }

    @Override
    protected int getServerPort() {
        return view.getServerPort();
    }

    @Override
    protected String getUserName() {
        return view.getUserName();
    }

    //21.6.
    public ClientGuiModel getModel() {
        return model;
    }

    //21.7.
    public static void main(String[] args) {
        ClientGuiController controller = new ClientGuiController();
        controller.run();
    }
}
