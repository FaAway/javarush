package com.javarush.test.level18.lesson05.task03;


/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //String[] fileNames = sc.nextLine().split(" ");
        FileInputStream f1 = new FileInputStream(sc.nextLine());
        FileOutputStream f2 = new FileOutputStream(sc.nextLine());
        FileOutputStream f3 = new FileOutputStream(sc.nextLine());
        sc.close();
        int f1Size = f1.available();
        int roundHalfCount = f1Size / 2 + f1Size % 2;
        for (int i = 0; i < roundHalfCount; i++){
            f2.write(f1.read());
        }
        for (int i = roundHalfCount; i < f1Size; i++){
            f3.write(f1.read());
        }
        f1.close();
        f2.close();
        f3.close();
    }
}
