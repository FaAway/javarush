package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        //Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        //sc.close();

        SortedMap<String, Double> map = new TreeMap<String, Double>();
        String s = null;
        while ((s = in.readLine()) != null) {
            String key = s.split(" ")[0];
            Double value = Double.parseDouble(s.split(" ")[1]);
            Double currentValue = map.get(key);
            if (currentValue == null) currentValue = 0.0;
            map.put(key, value + currentValue);
        }
        in.close();

        Iterator<SortedMap.Entry<String, Double>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            SortedMap.Entry<String, Double> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
