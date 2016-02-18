package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        //...
        try (
        Scanner sc = new Scanner(System.in);
        BufferedReader r = new BufferedReader(new FileReader(sc.nextLine()));
        ){
            ArrayList<String> list = new ArrayList<>();
            while (r.ready())
                list.addAll(Arrays.asList(r.readLine().split(" ")));
            String[] strings = new String[list.size()];
            strings = list.toArray(strings);
            StringBuilder result = getLine(strings);
            System.out.println(result.toString());
        }
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        if (words.length == 0) return sb;
        sb.append(words[0]);
        for (int j = 1; j < words.length; j++) {
            for (int i = 1; i < words.length; i++) {
                String word = words[i];
                if (word != null && word.toLowerCase().charAt(0) == sb.charAt(sb.length() - 1)) {
                    sb.append(" " + word);
                    words[i] = null;
                } else if (word != null && word.toUpperCase().charAt(word.length() - 1) == sb.charAt(0)) {
                    sb.insert(0, word + " ");
                }
            }
        }
        return sb;
    }
}
