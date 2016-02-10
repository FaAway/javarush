package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        int sum = 0;
        int number;
        String str;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            str = reader.readLine();
            if (str.equals("сумма")) break;
            number = Integer.parseInt(str);
            sum += number;
        }
        System.out.println(sum);
    }
}
