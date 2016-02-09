package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        //напишите тут ваш код
        String[] strings = s.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() != 0)
                strings[i] = Character.toUpperCase(strings[i].charAt(0)) + strings[i].substring(1);
        }
        StringBuilder sb = new StringBuilder();
        for (String str:
             strings) {
            sb.append(str + " ");
        }
        System.out.println(sb.toString());
    }


}
