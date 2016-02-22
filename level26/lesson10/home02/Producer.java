package com.javarush.test.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by FarAway on 21.02.2016.
 */
public class Producer implements Runnable {
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        int i = 0;
        while (!currentThread.isInterrupted()) {
            i++;
            try {
                map.put(String.valueOf(i), "Some text for " + i);
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(String.format("[%s] thread was terminated", currentThread.getName()));
            }
        }
    }
}
