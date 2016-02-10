package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }

            char[] charsArray = new char[sb.length()];
            sb.getChars(0, sb.length(), charsArray, 0);
            int englishLettersCount = 0;
            for (char ch: charsArray) {
                if ((ch >= 'A' && ch <= 'Z') || (ch >='a' && ch <='z'))
                    englishLettersCount++;
            }
            System.out.println(englishLettersCount);
            reader.close();
        } catch (Exception e) {

        }
    }
}
