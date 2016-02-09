package com.javarush.test.level18.lesson08.task04;

/**
 * Created by FarAway on 03.02.2016.
 */
public class Solution {
    public static void main(String[] args) {
        try {
            TxtInputStream txtstream = new TxtInputStream("input.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
