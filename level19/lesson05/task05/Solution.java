package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, включая символы новой строки. Результат вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader(sc.nextLine()));
        BufferedWriter out = new BufferedWriter(new FileWriter(sc.nextLine()));
        sc.close();
        //StringBuilder sb = new StringBuilder();
        String s;
        while (in.ready()) {
            char c = (char) in.read();
            if (!Pattern.matches("\\p{P}",String.valueOf(c))) {
                out.write(c);
            }
        }
        in.close();
        out.close();
    }
}
