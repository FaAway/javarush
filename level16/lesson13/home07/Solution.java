package com.javarush.test.level16.lesson13.home07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* Поиграем?
Три человека играют в игру. Каждый игрок(Gamer) характеризуется двумя параметрами: фамилией(name) и количеством действий в секунду (rating).
Нужно вывести в консоль ход игры и определить победителя и проигравших.
Итак...
1. Разберись, что делает программа.
1.1. List<String> steps хранит последовательность действий, которое каждый игрок выполняет от 0 до последнего.
1.2. isWinnerFound показывает, найден победитель или нет.
1.3. метод sleep выбрасывает InterruptedException и принимает параметр типа long.
1.4. Игроки играют независимо друг от друга.

2. Реализуйте логику метода run так, чтобы для каждого игрока:
2.1. За 1 секунду через равные интервалы времени выводились в консоль действия, описанные в steps. Количество выведенных действий должно равняться rating.
2.2. Любой текст должен начинаться с фамилии игрока (метод getName()), потом следовать двоеточие, а затем сам текст. Пример: [Ivanov:Начало игры].
2.3. Когда игрок выполнит все действия из steps, то он считается победителем. Выведите [getName() + ":победитель!"].
2.4. Когда найден победитель, то игра останавливается, и остальные игроки считаются побежденными. Выведите для них [getName() + ":проиграл"].
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        OnlineGame onlineGame = new OnlineGame();
        onlineGame.start();
    }

    public static class OnlineGame extends Thread {
        public static volatile boolean isWinnerFound = false;

        public static List<String> steps = new ArrayList<String>();

        static {
            steps.add("Начало игры");
            steps.add("Сбор ресурсов");
            steps.add("Рост экономики");
            steps.add("Убийство врагов");
        }

        protected Gamer gamer1 = new Gamer("Ivanov", 3);
        protected Gamer gamer2 = new Gamer("Petrov", 1);
        protected Gamer gamer3 = new Gamer("Sidorov", 5);

        public void run() {
            gamer1.start();
            gamer2.start();
            gamer3.start();

            while (!isWinnerFound) {
            }
            gamer1.interrupt();
            gamer2.interrupt();
            gamer3.interrupt();
        }
    }

    // Return intervals in milliseconds
    public static class RandomIntervalsPerSecondGenerator {
        private Random rnd = new Random(6565767);
        private int rating;
        private float[] normIntervals;
        private float checkSumOfIntervals;
        private long[] intervals;
        private int currentInterval = 0;

        public RandomIntervalsPerSecondGenerator(int rating) {
            this.rating = rating;
            normIntervals = new float[rating];
            intervals = new long[rating];
        }

        public long[] generateIntervals() throws MyWrongIntervalsException {

            for (int i = 0; i < rating; i++) {
                normIntervals[i] =  rnd.nextFloat() / rating * 2;
                checkSumOfIntervals += normIntervals[i];
            }

            float normalizator = 0.99f / checkSumOfIntervals;
            checkSumOfIntervals = 0;
            for (int i = 0; i < rating; i++) {
                normIntervals[i] *= normalizator;
                checkSumOfIntervals += normIntervals[i];
                intervals[i] = (int) (1000 * normIntervals[i]);
            }
            if (checkSumOfIntervals > 1) throw new MyWrongIntervalsException();

            return intervals;
        }

        public long nextInterval() throws MyWrongIntervalsException {
            if (currentInterval >= rating) currentInterval = 0;
            if (currentInterval == 0) generateIntervals();
            currentInterval++;
            return intervals[currentInterval - 1];
        }
    }

    static class MyWrongIntervalsException extends Exception {
        @Override
        public String toString() {
            return "Wrong sum: above 1.0f. Should be equal or below 1.0f";
        }
    }

    public static class Gamer extends Thread {
        private int rating;
        private int step;
        private RandomIntervalsPerSecondGenerator intervalsGen;

        public Gamer(String name, int rating) {
            super(name);
            this.rating = rating;
            intervalsGen = new RandomIntervalsPerSecondGenerator(rating);
        }

        @Override
        public void run() {
            //Add your code here - добавь код тут
            try {
                for (int k = 0; k < OnlineGame.steps.size(); k++) {
                    System.out.println(getName() + ":" + OnlineGame.steps.get(k));
                    Thread.sleep(1000 / rating);
                }
            }
            catch (InterruptedException e) {
                    System.out.println(getName() + ":проиграл");
                    return;
            }
            System.out.println(getName() + ":победитель!");
            OnlineGame.isWinnerFound = true;
        }
    }
}
