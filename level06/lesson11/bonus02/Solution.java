package com.javarush.test.level06.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Нужно добавить в программу новую функциональность
Задача: У каждой кошки есть имя и кошка-мама. Создать класс, который бы описывал данную ситуацию. Создать два объекта: кошку-дочь и кошку-маму. Вывести их на экран.
Новая задача: У каждой кошки есть имя, кошка-папа и кошка-мама. Изменить класс Cat так, чтобы он мог описать данную ситуацию.
Создать 6 объектов: маму, папу, сына, дочь, бабушку(мамина мама) и дедушку(папин папа).
Вывести их всех на экран в порядке: дедушка, бабушка, папа, мама, сын, дочь.

Пример ввода:
дедушка Вася
бабушка Мурка
папа Котофей
мама Василиса
сын Мурчик
дочь Пушинка

Пример вывода:
Cat name is дедушка Вася, no mother, no father
Cat name is бабушка Мурка, no mother, no father
Cat name is папа Котофей, no mother, father is дедушка Вася
Cat name is мама Василиса, mother is бабушка Мурка, no father
Cat name is сын Мурчик, mother is мама Василиса, father is папа Котофей
Cat name is дочь Пушинка, mother is мама Василиса, father is папа Котофей
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String granddaName = reader.readLine();
        Cat catGrandda = new Cat(granddaName, null, null);
        String grandmaName = reader.readLine();
        Cat catGrandma = new Cat(grandmaName, null, null);

        String fatherName = reader.readLine();
        Cat catFather = new Cat(fatherName, catGrandda, null);
        String motherName = reader.readLine();
        Cat catMother = new Cat(motherName, null, catGrandma);

        String sonName = reader.readLine();
        Cat catSon = new Cat(sonName, catFather, catMother);
        String daughterName = reader.readLine();
        Cat catDaughter = new Cat(daughterName, catFather, catMother);

        System.out.println(catGrandda);
        System.out.println(catGrandma);
        System.out.println(catFather);
        System.out.println(catMother);
        System.out.println(catSon);
        System.out.println(catDaughter);
    }

    public static class Cat
    {
       /* static ArrayList<String> maleFamlilyRole = new ArrayList<String>(2); {
        maleFamlilyRole.add("сын");
        maleFamlilyRole.add("папа");
        maleFamlilyRole.add("дедушка");};
        static ArrayList<String> femaleFamlilyRole = new ArrayList<String>(2); {
        femaleFamlilyRole.add("дочь");
        femaleFamlilyRole.add("мама");
        femaleFamlilyRole.add("бабушка");};*/

        private String name;
        //String familyRole;
        private Cat parentMale;
        private Cat parentFemale;
        //private int gender; // 0 - male, 1 - female

        Cat(String name)
        {
            this.name = name;
        }

        Cat(String name, Cat parentMale, Cat parentFemale)
        {
            this.name = name;
            //this.familyRole = familyRole;
            this.parentMale = parentMale;
            this.parentFemale = parentFemale;
        }

        @Override
        public String toString()
        {
            return "Cat name is " + name +
                    ((parentFemale == null)? ", no mother" : ", mother is " + parentFemale.name) +
                    ((parentMale == null)? ", no father" : ", father is " + parentMale.name);
        }
    }

}
