package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        String gender;
        int age;
        String nationality;
        String name;
        String secondName;
        String lastName;
        //напишите тут ваши переменные и конструкторы
        public Human() {

        }
        public Human(boolean gender) {

        }
        public Human(int age) {

        }
        public Human(String nationality) {

        }
        public Human(boolean gender, String name) {

        }
        public Human(boolean gender, String firstName, String secondName) {

        }
        public Human(boolean gender, String firstName, int age) {

        }
        public Human(boolean gender, String firstName, String secondName, int age) {

        }
        public Human(boolean gender, String firstName, String secondName, int age, String nationality) {

        }
        public Human(boolean gender, String firstName, String secondName, String lastName, int age, String nationality) {

        }
    }
}
