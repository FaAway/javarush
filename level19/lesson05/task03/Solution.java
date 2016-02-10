package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import com.sun.org.apache.xpath.internal.operations.Number;

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader(sc.nextLine()));
        BufferedWriter out = new BufferedWriter(new FileWriter(sc.nextLine()));
        sc.close();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = in.readLine()) != null)
            sb.append(s + " ");
        in.close();
        String[] words = sb.toString().split(" ");
        for (String str: words) {
            try {
                Integer.parseInt(str);
                out.write(str + " ");
            } catch (NumberFormatException e) {}
        }
        out.close();
    }
}
