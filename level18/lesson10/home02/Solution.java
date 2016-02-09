package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                sb.append(s + "\n");
            }

            char[] charsArray = new char[sb.length() - 1];
            sb.getChars(0, sb.length() - 1, charsArray, 0); //exclude last "\n" which doesn't exist in file
            int spaceLettersCount = 0;
            for (char ch: charsArray) {
                if (ch == ' ')
                    spaceLettersCount++;
            }
            System.out.printf(Locale.ENGLISH, "%.2f", 100.0 * spaceLettersCount / charsArray.length);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
