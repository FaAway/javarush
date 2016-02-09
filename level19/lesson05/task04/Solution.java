package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader(sc.nextLine()));
        BufferedWriter out = new BufferedWriter(new FileWriter(sc.nextLine()));
        sc.close();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = in.readLine()) != null)
            out.write(s.replace('.', '!'));
        in.close();
        out.close();
    }
}
