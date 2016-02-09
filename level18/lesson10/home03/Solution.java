package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName1 = sc.nextLine();
        String fileName2 = sc.nextLine();
        String fileName3 = sc.nextLine();
        sc.close();
        try {
            byte[] buf = new byte[4096];
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName1));
            BufferedInputStream in1 = new BufferedInputStream(new FileInputStream(fileName2));
            BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(fileName3));
            while (in1.available() > 0) {
                int count = in1.read(buf);
                out.write(buf, 0, count);
            }
            in1.close();
            while (in2.available() > 0) {
                int count = in2.read(buf);
                out.write(buf, 0, count);
            }
            in2.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
