package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by FarAway on 10.03.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;
    public BinaryRepresentationTask(int x) { this.x = x;}

    @Override
    protected String compute() {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return  (new BinaryRepresentationTask(b)).fork().join() + result;
        }
        return result;
    }
}
