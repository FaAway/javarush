package com.javarush.test.level08.lesson11.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: «May is 5 month».
Используйте коллекции.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //напишите тут ваш код
        Map<String, Integer> months = new HashMap<String, Integer>();
        months.put("january", 1);
        months.put("febrary", 2);
        months.put("march", 3);
        months.put("april", 4);
        months.put("may", 5);
        months.put("june", 6);
        months.put("july", 7);
        months.put("august", 8);
        months.put("september", 9);
        months.put("october", 10);
        months.put("november", 11);
        months.put("december", 12);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();
        int nMonth = months.get(month.toLowerCase()).intValue();
        System.out.println(month + " is " + nMonth + " month");
    }

}
