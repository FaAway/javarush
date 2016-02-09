package com.javarush.test.level17.lesson10.home02;

/**
 * Created by FarAway on 29.01.2016.
 */
public class Solution {
    public static void main(String[] args) {
        Beach[] beaches =  {
                new Beach("Beach nearest, high quality", 1, 10),
                new Beach("Beach farest, lowerst quality", 10, 1),
                new Beach("Beach medium, high quality", 5, 10),
                new Beach("Beach medium, low quality", 5, 1),
                new Beach("2nd Beach medium, low quality", 5, 1),
                new Beach("Beach nearest, low quality", 1, 1)
        };
        for (int i = 0; i < beaches.length; i++) {
            for (int j = i; j < beaches.length; j++) {
                System.out.print(beaches[i].getName());
                switch (beaches[i].compareTo(beaches[j])) {
                    case -1:
                        System.out.print(" < ");
                        break;
                    case 0:
                        System.out.print(" = ");
                        break;
                    case 1:
                        System.out.print(" > ");
                        break;
                }
                System.out.println(beaches[j].getName());
            }
        }
    }
}
