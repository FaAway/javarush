package com.javarush.test.level10.lesson11.bonus03;

import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

/* Задача по алгоритмам
Задача: ввести с клавиатуры 30 чисел. Вывести 10-е и 11-е минимальные числа.
Пояснение:
Самое минимальное число – 1-е минимальное.
Следующее минимальное после него – 2-е минимальное
Пример:
1 6 5  7  1  15   63   88
Первое минимальное – 1
Второе минимальное – 1
Третье минимальное – 5
Четвертое минимальное – 6
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[30];
        for (int i = 0; i < 30; i++)
        {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        System.out.println(array[9]);
        System.out.println(array[10]);
    }

    public static void sort(int[] array)
    {
        //напишите тут ваш код
        //qsort(array, 0, array.length - 1);
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j+1]) {
                    int swap = array[j];
                    array[j] = array[j+1];
                    array[j+1] = swap;
                }
            }
        }
    }

    public static void qsort(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int middle = array[(r + l) / 2];
        while (l <= r) {
            while (array[l] < middle) l++;
            while (array[r] > middle) r--;
            if (l == r) return;
            if (l <= r) {
                int swap = array[l];
                array[l] = array[r];
                array[r] = swap;
                l++;
                r--;
            }
        }
        if (left < r) qsort(array, left, r);
        if (l < right) qsort(array, l, right);
    }
}
