package com.javarush.test.level25.lesson09.task03;

import java.util.ArrayList;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        t.interrupt();
        ArrayList<Throwable> exceptionList = new ArrayList<>();
        exceptionList.add(e);
        Throwable nextThrowable = e.getCause();
        while (nextThrowable != null) {
            exceptionList.add(0, nextThrowable);
            nextThrowable = nextThrowable.getCause();
        }
        for (Throwable throwable : exceptionList) {
            System.out.println(throwable.getClass().getName() + ": " + throwable.getMessage());
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        sol.genException();
    }

    public void genException() {
        //new Error();
        Solution sol = new Solution();
        sol.uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
