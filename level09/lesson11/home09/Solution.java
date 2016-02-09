package com.javarush.test.level09.lesson11.home09;

import java.util.*;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        //напишите тут ваш код
        HashMap<String, Cat> catMap = new HashMap<String, Cat>();
        catMap.put("Vaska", new Cat("Vaska"));
        catMap.put("Murka", new Cat("Murka"));
        catMap.put("Sonya", new Cat("Sonya"));
        catMap.put("Rus", new Cat("Rus"));
        catMap.put("Skis", new Cat("Skis"));
        catMap.put("Bob", new Cat("Bob"));
        catMap.put("A", new Cat("A"));
        catMap.put("B", new Cat("B"));
        catMap.put("C", new Cat("C"));
        catMap.put("D", new Cat("D"));
        return catMap;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        //напишите тут ваш код
        HashSet<Cat> catSet = new HashSet<Cat>();
        Iterator<Map.Entry<String, Cat>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            catSet.add(iterator.next().getValue());
        }
        return catSet;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
