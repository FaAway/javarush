package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    private static int lineLength = 8 + 30 + 8 + 4;
    public static void main(String[] args) throws Exception {
        if (args.length < 4)
            return;
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        sc.close();
        processParams(args, fileName);
    }

    public static int findMaxId(String fileName) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(fileName));
        int id = 0;
        String s;
        while ((s = f.readLine()) != null) {
            if (s.length() < lineLength) continue;
            id = Math.max(id, Integer.parseInt(s.substring(0, 8).trim()));
        }
        f.close();
        return id;
    }

    public static void processParams(String[] args, String fileName) throws IOException{
        int id = findMaxId(fileName) + 1;
        String productName = args[1];
        double price = 0.0;
        int quantity = 0;
        int i;
        for (i = 2; i < lineLength; i++) {
            try {
                price = Double.parseDouble(args[i]);
                break;
            } catch (NumberFormatException e) {
                productName += " " +args[i];
            }
        }
        try {
            quantity = Integer.parseInt(args[++i]);}
        catch (NumberFormatException e) {
            System.out.println("quantity: NumberFormatException " + e.getMessage());
        }
        BufferedReader r = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder sb = new StringBuilder(r.readLine());
        while ((s = r.readLine()) != null)
            sb.append("\r\n" + s);
        BufferedWriter f = new BufferedWriter(new FileWriter(fileName));
        String newLine = String.format(Locale.ENGLISH, "%-8d%-30s%-8.2f%-4d", id, productName, price, quantity);
        f.write(sb.toString());
        f.write("\r\n" + newLine);//Charset.forName("CP1251"))
        f.close();
    }
}
