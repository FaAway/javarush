package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {
        threads.add(new InfinityThread());
        threads.add(new InterruptedThread());
        threads.add(new UraThread());
        threads.add(new MessageThread());
        threads.add(new ReadNumbersThread());
    }

    public static void main(String[] args) {
        for (Thread thread: threads) {
            thread.start();
        }
    }

    public static class InfinityThread extends Thread {
        public void run() {
            try {
                while (true) {
                    this.sleep(10);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static class InterruptedThread extends Thread {
        public void run() {
            try {
                    while (true) {
                    this.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class UraThread extends Thread {
        public void run() {
            try {
                while (true) {
                    this.sleep(500);
                    System.out.println("Ура");
                }
            } catch (InterruptedException e ) {

            }
        }
    }

    public static class MessageThread extends Thread implements Message{
        @Override
        public void showWarning() {
            if (isAlive())
                this.interrupt();
            while (isAlive()) {}
        }

        public void run() {
            try {
                while (true) {
                    sleep(10);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static class ReadNumbersThread extends Thread{
        public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s;
                    int sum = 0;
                    while (!(s = reader.readLine()).equals("N")) {
                        sum += Integer.parseInt(s);
                    }
                    reader.close();
                    System.out.println(sum);
                } catch (IOException e ) {

                }
        }
    }
}
