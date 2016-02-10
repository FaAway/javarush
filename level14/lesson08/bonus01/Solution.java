package com.javarush.test.level14.lesson08.bonus01;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //2
        try {
            String s = null;
            char ch = s.charAt(1);
        } catch (Exception e) {
            exceptions.add(e);
        }

        //3
        try {
            FileInputStream fIS = new FileInputStream("noname.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }

        //4
        try {
            simpleInterface b = new derivedFromSimpleInterface();
            ((derivedFromSimpleInterfaceWithB)b).g();
        } catch (Exception e) {
            exceptions.add(e);
        }

        //5
        try {
            int a[] = {1, 2, 3};
            int b = a[3];
        } catch (Exception e) {
            exceptions.add(e);
        }

        //6
        try {
            int g = Solution.multAB(5, (int)(Object)(""));
        } catch (Exception e) {
            exceptions.add(e);
        }

        //7
        try {
            double d = new Double("1s00sd");
        } catch (Exception e) {
            exceptions.add(e);
        }

        //8
        try {
            throw new MyException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        //9
        try {
            throw new MyException2();
        } catch (Exception e) {
            exceptions.add(e);
        }

        //10
        try {
            throw new MyException3();
        } catch (Exception e) {
            exceptions.add(e);
        }
    }

    interface simpleInterface {
        void g();
    }

    static class derivedFromSimpleInterfaceWithB implements simpleInterface {
        public void g() {}
        public void b() {}
    }

    static class derivedFromSimpleInterface implements simpleInterface {
        public void g() {}
    }

    public static int multAB(int a, int b) {
        return a * b;
    }

    public static class MyException extends Exception {

    }

    public static class MyException2 extends Exception {

    }

    public static class MyException3 extends Exception {

    }
}
