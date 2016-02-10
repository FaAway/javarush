package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println(System.getProperty("user.dir"));
        BufferedReader in = new BufferedReader(new java.io.FileReader(reader.readLine()));
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        String s = in.readLine();
        try {
            while (s != null) {
                arrayList.add(Integer.parseInt(s));
                s = in.readLine();
            }
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        ArrayList<Integer> sortedOddList = sortOdd(arrayList);
        for (int i = 0; i < sortedOddList.size(); i++) {
            System.out.println(sortedOddList.get(i));
        }
    }

    public static ArrayList<Integer> sortOdd(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) result.add(list.get(i));
        }

        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - 1; j++) {
                if (result.get(j) > result.get(j + 1)) {
                    Integer a = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, a);
                }
            }
        }
        return result;
    }
}
