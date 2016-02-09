package com.javarush.test.level15.lesson12.bonus01;

/**
 * Created by FarAway on 20.01.2016.
 */
public class Plane implements Flyable {
    private int passangers = 0;

    public Plane(int passangers) {
        this.passangers = passangers;
    }

    @Override
    public void fly() {

    }
}
