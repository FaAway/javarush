package com.javarush.test.level14.lesson08.home05;

/**
 * Created by FarAway on 19.01.2016.
 */
public class Computer {
    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    private Keyboard keyboard;
    private Mouse mouse;
    private Monitor monitor;

    public Computer(Keyboard keyboard, Mouse mouse, Monitor monitor) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.monitor = monitor;
    }

    public Computer() {
        keyboard = new Keyboard();
        mouse = new Mouse();
        monitor = new Monitor();
    }
}
