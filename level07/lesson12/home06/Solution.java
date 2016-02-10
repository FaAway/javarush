package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        ArrayList<Human> family = new ArrayList<Human>();
        family.add(new Human("Игорь", true, 70, null, null));
        family.add(new Human("Вера", false, 65, null, null));
        family.add(new Human("Коля", true, 75, null, null));
        family.add(new Human("Наташа", false, 71, null, null));
        family.add(new Human("Володя", true, 50, (Human)family.get(0), (Human)family.get(1)));
        family.add(new Human("Надя", false, 45, (Human)family.get(2), (Human)family.get(3)));
        family.add(new Human("Петя", true, 20, (Human)family.get(4), (Human)family.get(5)));
        family.add(new Human("Катя", false, 15, (Human)family.get(4), (Human)family.get(5)));
        family.add(new Human("Лена", false, 12, (Human)family.get(4), (Human)family.get(5)));

        for (Human human : family) {
            System.out.println(human.toString());
        }
    }

    public static class Human
    {
        //напишите тут ваш код
        public String name;
        public boolean sex;
        public int age;
        public Human father;
        public Human mother;

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
