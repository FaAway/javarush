package com.javarush.test.level27.lesson15.big01;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by FarAway on 29.02.2016.
 */
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Tablet tablet = tablets.get((int)(ThreadLocalRandom.current().nextDouble(1) * tablets.size()));
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
