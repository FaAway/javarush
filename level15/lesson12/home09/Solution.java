package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
shttp://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
shttp://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        String[] paramStrings = url.substring(url.indexOf('?') + 1).split("&");
        for (int i = 0; i < paramStrings.length; i++) {
            System.out.print(((i > 0)? " ": "") + paramStrings[i].split("=")[0]);
        }

        System.out.println();

        for (int i = 0; i < paramStrings.length; i++) {
            //System.out.print(paramStrings[i] + " ");
            if (paramStrings[i].startsWith("obj")) {

                String paramValue = paramStrings[i].substring(paramStrings[i].indexOf("=") + 1);
                    try {
                        alert(Double.parseDouble(paramValue));
                    } catch (Exception e) {
                        alert(paramValue);
                    }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
