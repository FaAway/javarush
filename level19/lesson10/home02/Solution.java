package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        //sc.close();

        SortedMap<String, Double> map = new TreeMap<>();
        String s = null;
        while ((s = in.readLine()) != null) {
            String key = s.split(" ")[0];
            Double value = Double.parseDouble(s.split(" ")[1]);
            Double currentValue = map.get(key);
            if (currentValue == null) currentValue = 0.0;
            map.put(key, value + currentValue);
        }
        in.close();

        List list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return (a.getValue() < (Double)b.getValue()) ? -1 : ((a.getValue() == (Double)b.getValue()) ? 0 : +1);
            }
        });

        Double max = - Double.MAX_VALUE;
        for (Object o: list) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>)o;
            if (entry.getValue() >= max) {
                max = entry.getValue();
            }
        }

        for (Object o: list) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>)o;
            if (entry.getValue() == max) {
                System.out.println(entry.getKey());
            }
        }

    }
}
