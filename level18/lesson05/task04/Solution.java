package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        FileInputStream f1 = new FileInputStream(sc.nextLine());
        FileOutputStream f2 = new FileOutputStream(sc.nextLine());
        sc.close();
        byte[] buf = new byte[f1.available()];
        f1.read(buf);
        for (int i = buf.length -1; i >= 0; i--){
            f2.write(buf[i]);
        }
        f1.close();
        f2.close();
    }
}
