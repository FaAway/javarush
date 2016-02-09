package com.javarush.test.level14.lesson08.home03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* User, Looser, Coder and Proger
1. Ввести [в цикле] с клавиатуры несколько строк (ключей).
Строки(ключи) могут быть такими: "user", "looser", "coder", "proger".
Ввод окончен, когда строка не совпадает ни с одной из выше указанных.

2. Для каждой введенной строки нужно:
2.1. Создать соответствующий объект [см Person.java], например, для строки "user" нужно создать объект класса User.
2.2. Передать этот объект в метод doWork.

3. Написать реализацию метода doWork, который:
3.1. Вызывает метод live() у переданного обекта, если этот объект (person) имеет тип User.
3.2. Вызывает метод doNothing(), если person имеет тип Looser.
3.3. Вызывает метод coding(), если person имеет тип Coder.
3.4. Вызывает метод enjoy(), если person имеет тип Proger.
*/

public class Solution
{
    public static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;
        //ArrayList<String> keys = new ArrayList<String>();
        //System.out.println(Person.User.class.getCanonicalName());
        //тут цикл по чтению ключей, пункт 1
        {
            String keysVariants = "\"user\", \"looser\", \"coder\", \"proger\"";
            while (keysVariants.contains(key = reader.readLine())) {
                //keys.add(key);
                /*switch (key) {
                    case "user": person = new Person.User();
                        break;
                    case "looser": person = new
                }*/

                person = (Person)Class.forName("com.javarush.test.level14.lesson08.home03.Person$" + firstUpperCase(key)).newInstance();
                doWork(person); //вызываем doWork
            }
        //создаем объект, пункт 2
        }
    }

    public static void doWork(Person person)
    {
        // пункт 3
        if (Person.User.class.isInstance(person)) ((Person.User)person).live();
        else if (Person.Looser.class.isInstance(person)) ((Person.Looser)person).doNothing();
        else if (Person.Coder.class.isInstance(person)) ((Person.Coder)person).coding();
        else if (Person.Proger.class.isInstance(person)) ((Person.Proger)person).enjoy();
    }
}
