package com.javarush.test.level30.lesson02.home01;

public enum NumerationSystemType implements NumerationSystem {
    _16,
    _12,
    _10,
    _9,
    _8,
    _7,
    _6,
    _5,
    _4,
    _3,
    _2;

    static char[] numerics = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @Override
    public int getNumerationSystemIntValue() {
        return Integer.parseInt(this.name().substring(1));
    }
}
