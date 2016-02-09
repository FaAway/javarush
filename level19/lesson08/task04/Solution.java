package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out = System.out;
        ByteArrayOutputStream myByteOut = new ByteArrayOutputStream();
        PrintStream myOut = new PrintStream(myByteOut);
        System.setOut(myOut);
        testString.printSomething();
        System.setOut(out);
        String[] params =  myByteOut.toString().split(" ");
        Integer n1 = Integer.parseInt(params[0]), n2 = Integer.parseInt(params[2]);
        Integer result = 0;
        switch (params[1]) { //switching operand
            case "*": result = n1 * n2; break;
            case "+": result = n1 + n2; break;
            case "-": result = n1 - n2; break;
        }
        System.out.println(myByteOut.toString().trim() + " " + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

