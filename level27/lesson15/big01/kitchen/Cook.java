package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by FarAway on 27.02.2016.
 */
public class Cook implements Runnable{
    private String name;
    private LinkedBlockingQueue<Order> queue;
    private LinkedBlockingQueue<Order> deliveryQueue;
    private volatile boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void setDeliveryQueue(LinkedBlockingQueue<Order> deliveryQueue) {
        this.deliveryQueue = deliveryQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void startCookingOrder(Order order) {
        busy = true;

        Tablet tablet = order.getTablet();

        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        try {
            Thread.sleep(10 * order.getTotalCookingTime());
        } catch (InterruptedException e) {

        }
        //When finish cooking
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(tablet.toString(), this.toString(), order.getTotalCookingTime() * 60, order.getDishes()));

        order.setCookedBy(this);
        try {
            deliveryQueue.put(order);
        } catch (InterruptedException e) {}

        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }
}
