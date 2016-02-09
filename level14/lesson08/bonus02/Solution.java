package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int int1 = Integer.parseInt(reader.readLine());
        int int2 = Integer.parseInt(reader.readLine());
        int a = Math.max(int1, int2);
        int b = Math.min(int1, int2);
        while (a % b != 0) {
            int ost = a % b;
            a = b;
            b = ost;
        }
        int nod = b;
        System.out.println(b);
    }
}
