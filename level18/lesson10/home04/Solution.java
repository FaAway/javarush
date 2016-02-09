package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName1 = sc.nextLine();
        String fileName2 = sc.nextLine();
        sc.close();
        try {
            byte[] buf = new byte[4096];
            RandomAccessFile f1 = new RandomAccessFile(fileName1, "rw");
            BufferedInputStream f2 = new BufferedInputStream(new FileInputStream(fileName2));
            long f1Size = f1.length();
            long f2Size = f2.available();
            long toRead;
            long readOffset;
            if (f1Size > f2Size) {
                readOffset = f2Size;
                toRead = f1Size - f2Size;

                //moving the tail of f1 (exceeding f2 part) by offset of f2 size to avoid rewriting further
                while (toRead > 0) {
                    f1.seek(readOffset);
                    int readed = f1.read(buf, 0, (int)Math.min(toRead, buf.length));
                    toRead -=readed;
                    f1.seek(readOffset + f2Size); //write offset
                    f1.write(buf, 0, readed);
                    readOffset +=readed;
                };
            }
            //moving f1 (without tail if exists) by offset of f1
            readOffset = 0;
            toRead = Math.min(f1Size, f2Size);
            while (toRead > 0) {
                f1.seek(readOffset);
                int readed = f1.read(buf, 0, (int)Math.min(toRead, buf.length));
                toRead -=readed;
                f1.seek(readOffset + f2Size); //write offset
                f1.write(buf, 0, readed);
                readOffset +=readed;
            }

            // copying f2 to the beginning of f1
            f1.seek(0);
            while (f2.available() > 0) {
                int count = f2.read(buf);
                f1.write(buf, 0, count);
            }
            f1.close();
            f2.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
