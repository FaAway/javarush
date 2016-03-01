package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by FarAway on 27.02.2016.
 */
public class Tablet {
    private static java.util.logging.Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;
    private LinkedBlockingQueue<Order> queue;

    public int getNumber() {
        return number;
    }

    public Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void createOrder() {
        try {
            final Order newOrder = new Order(this);
            insideOrder(newOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return;
        }
    }

    private void insideOrder(Order newOrder) throws IOException {
        if (newOrder.isEmpty()) return;
        ConsoleHelper.writeMessage(newOrder.toString());
        try {
            queue.put(newOrder);
        } catch (InterruptedException e) {
            return;
        }
        try {
            new AdvertisementManager(newOrder.getTotalCookingTime() * 60).processVideos();
        } catch (NoVideoAvailableException e) {
            StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(newOrder.getTotalCookingTime()*60));
            logger.log(Level.INFO, "No video is available for the order " + newOrder);
        }
    }

    public void createTestOrder() {
        try {
            final Order newOrder = new TestOrder(this);
            insideOrder(newOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString() {
        return "Tablet{" + "number=" + number + '}';
    }
}
