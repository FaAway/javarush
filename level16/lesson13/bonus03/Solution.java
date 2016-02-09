package com.javarush.test.level16.lesson13.bonus03;

/* Отдебажим все на свете
Разобраться, что делает програма.
Почитать про UncaughtExceptionHandler - это важно.
Еще раз внимательно посмотреть программу.
Разобраться - продебажить - почему наш OurUncaughtExceptionHandler не срабатывает.
Исправить ошибку, т.е. все должно работать. :)

Ожидаемый результат в произвольном порядке:
Нить 1: My exception message
Нить 2: My exception message
*/

public class Solution {
    public static Thread.UncaughtExceptionHandler handler = new OurUncaughtExceptionHandler();

    static Thread[] getGroupThreads( final ThreadGroup group ) {
        if ( group == null )
            throw new NullPointerException( "Null thread group" );
        int nAlloc = group.activeCount( );
        int n = 0;
        Thread[] threads;
        do {
            nAlloc *= 2;
            threads = new Thread[ nAlloc ];
            n = group.enumerate( threads );
        } while ( n == nAlloc );
        return java.util.Arrays.copyOf( threads, n );
    }

    public static void main(String[] args) {
        TestedThread commonThread = new TestedThread(handler);
        ThreadGroup tg = new ThreadGroup(Thread.currentThread().getThreadGroup(), "ABThreadGroup");
        Thread threadA = new Thread(tg, commonThread, "Нить 1");
        Thread threadB = new Thread(tg, commonThread, "Нить 2");
        threadA.setUncaughtExceptionHandler(handler);
        threadB.setUncaughtExceptionHandler(handler);
        threadA.start();
        threadB.start();

//                ThreadGroup tg = Thread.currentThread().getThreadGroup();
//        Thread[] ths = Solution.getGroupThreads(tg);
//        for (Thread th: ths) {
//            System.out.println(th);
//        }
        threadA.interrupt();
        threadB.interrupt();
    }


    public static class TestedThread extends Thread {
        public TestedThread(UncaughtExceptionHandler handler) {
            setUncaughtExceptionHandler(handler);
            start();
        }

        public void run() throws RuntimeException {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException x) {
                throw new RuntimeException("My exception message");
            }
        }
    }

    public static class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + ": " + e.getMessage());
        }
    }
}
