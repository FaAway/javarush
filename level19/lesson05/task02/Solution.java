package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader( new FileReader(sc.nextLine()));
        sc.close();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = in.readLine()) != null)
            sb.append(s + ",");
        in.close();
        String[] words = sb.toString().split("\\W");
        int counter = 0;
        for (String str: words) {
            if (str.equals("world")) counter++;
        }
        System.out.println(counter);
    }
}
