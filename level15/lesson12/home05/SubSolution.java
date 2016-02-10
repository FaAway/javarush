package com.javarush.test.level15.lesson12.home05;

/**
 * Created by FarAway on 20.01.2016.
 */
public class SubSolution extends Solution {

    private SubSolution(boolean a) {

    }

    private SubSolution(boolean a, boolean b) {

    }

    private SubSolution(boolean a, float b) {

    }

    SubSolution(short a) {
        super(a);
    }

    SubSolution(String s) {
        super(s);
    }

    SubSolution(String s1, String s2) {
        super(s1, s2);
    }

    protected SubSolution(int a) {
        super(a);
    }

    protected SubSolution(int a, int b) {
        super(a, b);
    }

    protected SubSolution(int a, int b, int c) {
        super(a, b, c);
    }

    public SubSolution() {
    }

    public SubSolution(short a, short b) {
        super(a, b);
    }

    public SubSolution(short a, short b, short c) {
        super(a, b, c);
    }
}
