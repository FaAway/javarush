package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        //создай по два объекта каждого класса тут
        Man man1 = new Man("Mike", 27, "Cheboksary");
        Man man2 = new Man("Vano", 24, "Zelenograd");
        Woman woman1 = new Woman("Kate", 29, "Krasnogorsk");
        Woman woman2 = new Woman("Alina", 28, "Moskow");

        man1.print();
        man2.print();
        woman1.print();
        woman1.print();
        //выведи их на экран тут
    }

    //добавьте тут ваши классы

    public static class Man {
        private String name;
        private int age;
        private String address;

        public void initialize(String name,int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public Man() {
            initialize("", 0, "");
        }
        public Man(String name, int age, String address) {
            initialize(name, age, address);
        }
        public Man(String name) {
            initialize(name, 0, "");
        }

        public Man(String name, int age) {
            initialize(name, age, "");
        }

        public Man(String name, String address) {
            initialize(name, 0, address);
        }

        public Man(int age, String address) {
            initialize("", age, address);
        }

        public void print() {
            System.out.println(name + " " + age + " " + address);
        }
    }

    public static class Woman {
        private String name;
        private int age;
        private String address;

        public void initialize(String name,int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public Woman() {
            initialize("", 0, "");
        }
        public Woman(String name, int age, String address) {
            initialize(name, age, address);
        }
        public Woman(String name) {
            initialize(name, 0, "");
        }

        public Woman(String name, int age) {
            initialize(name, age, "");
        }

        public Woman(String name, String address) {
            initialize(name, 0, address);
        }

        public Woman(int age, String address) {
            initialize("", age, address);
        }

        public void print() {
            System.out.println(name + " " + age + " " + address);
        }
    }
}
