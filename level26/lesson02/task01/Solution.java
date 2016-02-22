package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int i_middle = array.length/2;
        final double mediana = (array.length % 2 == 1) ? array[i_middle] : (array[i_middle - 1] + array[i_middle]) / 2;
        Comparator<Integer> medianaRemoteness = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result =  (int)Math.round(Math.abs(mediana - o1) - Math.abs(mediana - o2));
                return (result == 0) ? (o1 - o2) : result;
            }
        };
        Arrays.sort(array, medianaRemoteness);
        return array;
    }

    public static void main(String[] args) {
        Integer[] array1 = {1,2,3,4,5};
        System.out.print(Arrays.asList(array1) + " -> ");
        System.out.println(Arrays.asList(sort(array1)));
        Integer[] array2 = {3,2,1};
        System.out.print(Arrays.asList(array2) + " -> ");
        System.out.println(Arrays.asList(sort(array2)));
        Integer[] array3 = {3,2,1,4};
        System.out.print(Arrays.asList(array3) + " -> ");
        System.out.println(Arrays.asList(sort(array3)));
        Integer[] array4 = {3,2};
        System.out.print(Arrays.asList(array4) + " -> ");
        System.out.println(Arrays.asList(sort(array4)));
        Integer[] array5 = {3};
        System.out.print(Arrays.asList(array5) + " -> ");
        System.out.println(Arrays.asList(sort(array5)));

    }
}
