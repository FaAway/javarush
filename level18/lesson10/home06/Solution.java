package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.BufferedReader;
import java.io.FileReader;

public class Solution {
        public static void main(String[] args) {
            int chCode = 0;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                String s = null;
                StringBuilder sb = new StringBuilder();
                while ((s = reader.readLine()) != null) {
                    sb.append(s + "\n");
                }
                char[] charsArray = new char[sb.length() - 1];
                sb.getChars(0, sb.length() - 1, charsArray, 0); //exclude last "\n" which doesn't exist in file
                int[] charsFreqArray = new int[256];
                for (char ch: charsArray) {
                    chCode = ch;
                    charsFreqArray[ch]++;
                }
                for (int i = 0; i < charsFreqArray.length; i++) {
                    if (charsFreqArray[i] > 0) {
                        System.out.println((char)i + " " + charsFreqArray[i]);
                    }
                }
                reader.close();
            } catch (Exception e) {
                System.out.println(chCode);
                e.printStackTrace();
            }
        }
}
