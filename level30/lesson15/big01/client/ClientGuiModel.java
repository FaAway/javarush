package com.javarush.test.level30.lesson15.big01.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FarAway on 12.03.2016.
 */

//20.1.
public class ClientGuiModel {
    //20.2.
    private final Set<String> allUserNames = new HashSet<>();
    //20.3.
    private String newMessage;

    //20.4.
    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames) ;
    }

    //20.5.
    public String getNewMessage() {
        return newMessage;
    }
    //20.5.
    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    //20.6.
    public void addUser(String newUserName) {
        allUserNames.add(newUserName);
    }

    //20.7.
    public void deleteUser(String userName) {
        allUserNames.remove(userName);
    }
}
