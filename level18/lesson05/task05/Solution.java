package com.javarush.test.level18.lesson05.task05;

/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки
2.2 выбросить исключение DownloadException
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<FileInputStream> files = new ArrayList<FileInputStream>();
        String fileName;
        while ((fileName = sc.nextLine()) != null) {
            FileInputStream f = new FileInputStream(fileName);
            files.add(f);
            if (f.available() < 1000) {
                for (FileInputStream tf : files) {
                    tf.close();
                    throw new DownloadException();
                }
            }
        }
    }

    public static class DownloadException extends Exception{

    }
}
