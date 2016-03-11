package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * Created by FarAway on 10.03.2016.
 */
public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {this.queue = queue;}

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Processing " + queue.take().toString());
            }
        } catch (InterruptedException e) {}
    }
}
