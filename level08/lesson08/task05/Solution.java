package com.javarush.test.level08.lesson08.task05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = createMap();
        removeTheFirstNameDuplicates(map);
        printHashMap(map);
    }

    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Stallone", "Silvestr");
        map.put("Leonardo", "Bob");
        map.put("Da Vinci", "Bob");
        map.put("Di Caprio", "Leonardo");
        map.put("Bella", "Donna");
        map.put("London", "Jack");
        map.put("Navalny", "Alexey");
        map.put("Petrov", "Alexandr");
        map.put("Sidorov", "Alexandr");
        map.put("Volkov", "Silvestr");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //напишите тут ваш код
        ArrayList<String> values = new ArrayList<String>();
        values.addAll(map.values());
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                if (i != j && values.get(i) != null && values.get(j) != null)
                    if (values.get(i).equals(values.get(j))) {
                        removeItemFromMapByValue(map, values.get(i));
                        values.set(i, null);
                        values.set(j, null);
                    }
            }
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void printHashMap(HashMap<String, String> map) {
        for (Iterator<Map.Entry<String, String>> iteratorI = map.entrySet().iterator(); iteratorI.hasNext(); ) {
            Map.Entry<String, String> pair = iteratorI.next();
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
    }
}
