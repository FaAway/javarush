package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by FarAway on 14.02.2016.
 */
public class Hippodrome {
    private static ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;
    public static void main(String[] args) {
        game = new Hippodrome();
        Horse horse1 = new Horse("First", 3, 0);
        Horse horse2 = new Horse("Second", 3, 0);
        Horse horse3 = new Horse("Third", 3, 0);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        game.run();
        game.printWinner();
    }

    public Horse getWinner() {
        int indexOfHorseMaxDist = 0;
        for (int i = 1; i < horses.size(); i++) {
            if (horses.get(i).getDistance() > horses.get(indexOfHorseMaxDist).getDistance())
                indexOfHorseMaxDist = i;
        }
        return horses.get(indexOfHorseMaxDist);
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                move();
                print();
                Thread.sleep(200);
            }
        }catch (InterruptedException e) {

        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
            horse.print();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
            System.out.println();
            System.out.println();
        }
    }
}
