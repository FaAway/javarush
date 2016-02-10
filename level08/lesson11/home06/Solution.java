package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Map<String, Human> bigFamily = new HashMap<String, Human>();
        bigFamily.put("grDaMa", new Human("Nikolay", true, 76));
        bigFamily.put("grDaDa", new Human("Nikolay", true, 80));
        bigFamily.put("grMaMa", new Human("Vera", false, 70));
        bigFamily.put("grMaDa", new Human("Shura", false, 95));
        bigFamily.put("Dad", new Human("Volodya", true, 59));
        bigFamily.put("Mom", new Human("Nadya", false, 50));
        bigFamily.put("Daughter", new Human("Katya", false, 29));
        bigFamily.put("seniorSon", new Human("Misha", true, 27));
        bigFamily.put("jrSon", new Human("Vanya", true, 23));
        
        bigFamily.get("grDaMa").addChild(bigFamily.get("Mom"));
        bigFamily.get("grDaDa").addChild(bigFamily.get("Dad"));
        bigFamily.get("grMaMa").addChild(bigFamily.get("Mom"));
        bigFamily.get("grMaDa").addChild(bigFamily.get("Dad"));
        bigFamily.get("Mom").addChild(bigFamily.get("Daughter"));
        bigFamily.get("Mom").addChild(bigFamily.get("seniorSon"));
        bigFamily.get("Mom").addChild(bigFamily.get("jrSon"));
        bigFamily.get("Dad").addChild(bigFamily.get("Daughter"));
        bigFamily.get("Dad").addChild(bigFamily.get("seniorSon"));
        bigFamily.get("Dad").addChild(bigFamily.get("jrSon"));

        Iterator<Map.Entry<String, Human>> iterator = bigFamily.entrySet().iterator();
        while (iterator.hasNext()){
             System.out.println(iterator.next().toString());
        }
    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public void addChild(Human child) {
            children.add(child);
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
