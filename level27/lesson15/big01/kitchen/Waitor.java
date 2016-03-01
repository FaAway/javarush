package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by FarAway on 27.02.2016.
 */
public class Waitor implements Runnable{
    private LinkedBlockingQueue<Order> deliveryQueue;

    public void setDeliveryQueue(LinkedBlockingQueue<Order> deliveryQueue) {
        this.deliveryQueue = deliveryQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Order order = deliveryQueue.take();
                ConsoleHelper.writeMessage(order + " was cooked by " + order.getCookedBy());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
