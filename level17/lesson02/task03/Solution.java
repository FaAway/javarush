package com.javarush.test.level17.lesson02.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by FarAway on 28.01.2016.
 */
public class Solution {
    public static int countThreads = 10;
    public static MyClass swapObject = new MyClass();
    public static Random rnd = new Random(47);

    public static void main(String[] args) throws InterruptedException {
        initThreads();
        System.out.println(swapObject);
    }

    public static void initThreads() throws InterruptedException {
        List<Thread> threads = new ArrayList<Thread>(countThreads);
        for (int i = 0; i < countThreads; i++) threads.add(new MyThread());
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

    public static class MyThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                if (rnd.nextInt(2) == 1) swapObject.swap(); else MyClass.swap2();
            }

        }
    }
}
