package com.javarush.test.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки. Не использовать try-with-resources

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
/*        words.add("А");
        words.add("Б");
        words.add("В");*/
    }

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(sc.readLine()));
        sc.close();
        String s = null;
        while (reader.ready()) {
            s = reader.readLine();
            String[] readedWords  = s.split(" ");
            int matcher = 0;
            for (int i = 0; i < readedWords.length; i++) {
                if(words.contains(readedWords[i]))
                    matcher++;
            }
            if(matcher == 2)
                System.out.println(s);
        }
        reader.close();
    }
}
