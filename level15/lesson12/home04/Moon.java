package com.javarush.test.level15.lesson12.home04;

/**
 * Created by FarAway on 20.01.2016.
 */
public class Moon implements Planet {
    private Moon() {}

    private static Moon instance;

    public synchronized static Moon getInstance() {
        if (instance == null)
            instance = new Moon();
        return instance;
    }
}
