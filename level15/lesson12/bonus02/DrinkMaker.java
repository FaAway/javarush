package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by FarAway on 20.01.2016.
 */
public abstract class DrinkMaker {
    abstract void getRightCup(); // - выбрать подходящую чашку
    abstract void putIngredient(); // - положить ингредиенты
    abstract void pour(); // - залить жидкостью

    public void makeDrink() {
        getRightCup();
        putIngredient();
        pour();
    }
}
