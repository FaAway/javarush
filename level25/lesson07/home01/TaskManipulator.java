package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator{
    private Thread t;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(0);
                System.out.println(t.getName());
                Thread.sleep(90);
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void start(String threadName) {
        t = new Thread(this, threadName);
        t.start();
    }

    @Override
    public void stop() {
        t.interrupt();
    }
}
