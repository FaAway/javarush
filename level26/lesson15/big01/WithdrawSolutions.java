package com.javarush.test.level26.lesson15.big01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarAway on 22.02.2016.
 */
class WithdrawSolutions {

    /*int[] values = {10,20,50,100,200};

    public static void main(String[] args) {
        int[] values = {10,20,50,100,200};
        int[] ammounts = {10,10,10,10,10};
        List<Integer[]> results = solutions(values, ammounts, new int[5], 180, 0);
        for (Integer[] result : results){
            System.out.println(Arrays.toString(result));
        }

    }*/

   /* This code having this ouput (it is output for 10,20,50,100,200 bank notes, you have 10 of each of them and you want to get 180 in sum)

            [10, 4, 0, 0, 0]
            [9, 2, 1, 0, 0]
            [8, 5, 0, 0, 0]
            [8, 0, 2, 0, 0]
            [8, 0, 0, 1, 0]
            [7, 3, 1, 0, 0]
            [6, 6, 0, 0, 0]
            [6, 1, 2, 0, 0]
            [6, 1, 0, 1, 0]
            [5, 4, 1, 0, 0]
            [4, 7, 0, 0, 0]
            [4, 2, 2, 0, 0]
            [4, 2, 0, 1, 0]
            [3, 5, 1, 0, 0]
            [3, 0, 3, 0, 0]
            [3, 0, 1, 1, 0]
            [2, 8, 0, 0, 0]
            [2, 3, 2, 0, 0]
            [2, 3, 0, 1, 0]
            [1, 6, 1, 0, 0]
            [1, 1, 3, 0, 0]
            [1, 1, 1, 1, 0]
            [0, 9, 0, 0, 0]
            [0, 4, 2, 0, 0]
            [0, 4, 0, 1, 0]*/

    public static List<Integer[]> withdrawSolutions(Integer[] values, Integer[] ammounts, int[] variation, int ammount, int position) {

        List<Integer[]> list = new ArrayList<>();
        int value = compute(values, variation);
        if (value < ammount) {
            for (int i = position; i < values.length; i++) {
                if (ammounts[i] > variation[i]) {
                    int[] newvariation = variation.clone();
                    newvariation[i]++;
                    List<Integer[]> newList = withdrawSolutions(values, ammounts, newvariation, ammount, i);
                    if (newList != null) {
                        list.addAll(newList);
                    }
                }
            }
        } else if (value == ammount) {
            list.add(myCopy(variation));
        }
        return list;
    }

    public static int compute(Integer[] values, int[] variation) {
        int ret = 0;
        for (int i = 0; i < variation.length; i++) {
            ret += values[i] * variation[i];
        }
        return ret;
    }

    public static Integer[] myCopy(int[] ar) {
        Integer[] ret = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ret[i] = ar[i];
        }
        return ret;
    }
}
