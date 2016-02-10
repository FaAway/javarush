package com.javarush.test.level17.lesson02.task03;

/**
 * Created by FarAway on 28.01.2016.
 */

class MyClass {
    private static String name1 = "Оля";
    private static String name2 = "Лена";

    public void swap() {
        synchronized (this) {
            String s = name1;
            name1 = name2;
            name2 = s;
        }
    }

    public static void swap2() {
        synchronized (MyClass.class) {
            String s = name1;
            name1 = name2;
            name2 = s;
        }
    }

    public String toString() {
        return "name1: " + name1 + "\nname2: " + name2;
    }
}