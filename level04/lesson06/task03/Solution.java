package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner sc = new Scanner(System.in);
        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());
        int n3 = Integer.parseInt(sc.nextLine());
        int n;
        for (int i = 0; i < 2; i++) {
            n = Math.min(n1, n2);
            n1 = n1 + n2 - n;
            n2 = n;
            n = Math.min(n2, n3);
            n2 = n2 + n3 - n;
            n3 = n;
        }
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
    }
}
