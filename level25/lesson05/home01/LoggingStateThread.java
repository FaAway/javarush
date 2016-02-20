package com.javarush.test.level25.lesson05.home01;

/**
 * Created by FarAway on 20.02.2016.
 */
public class LoggingStateThread extends Thread {
    private Thread target;
    public LoggingStateThread(Thread target)
    {
        setDaemon(true);
        this.target = target;
    }
    @Override
    public void run()
    {
        State state = target.getState();
        System.out.println(state);
        while(state != State.TERMINATED)
        {
            if (state != target.getState())
            {
                state = target.getState();
                System.out.println(state);
            }
        }
    }
}