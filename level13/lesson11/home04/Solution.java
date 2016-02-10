package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream output = new FileOutputStream(reader.readLine());

        do {
            String s = reader.readLine();
            String line = s + "\n";
            byte[] chars = line.getBytes();
            for (int i = 0; i < chars.length; i++) {
                output.write(chars[i]);
            }

            if (s.equals("exit")) break;
        } while (true);

        reader.close();

    }
}
