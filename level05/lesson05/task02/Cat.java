package com.javarush.test.level05.lesson05.task02;

/**
 * Created by FarAway on 20.11.2015.
 */
public class Cat
{
    public String name;
    public int age;
    public int weight;
    public int strength;

    public Cat()
    {
    }

    public boolean fight(Cat anotherCat)
    {
        boolean thiswin;
        //напишите тут ваш код
        if (this.strength > anotherCat.strength)
            thiswin = true;
        else if (this.strength == anotherCat.strength) {
            if (this.age > anotherCat.age) {
                thiswin = true;
            }
            else if (this.age == anotherCat.age) {
                if (this.weight > anotherCat.weight)
                    thiswin = true;
                else if (this.weight == anotherCat.weight) {
                    thiswin = this.name.compareTo(anotherCat.name) > 0;
                }
                else {
                    thiswin = false;
                }
            }
            else {
                thiswin = false;
            }
        }
        else {
            thiswin = false;
        }
        return thiswin;
    }
}