package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    /*public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Leonardo", new Date("JULY 12 1180"));
        map.put("Da Vinci", new Date("JULY 12 1180"));
        map.put("Di Caprio", new Date("MARCH 13 1981"));
        map.put("Bella", new Date("DECEMBER 30 1989"));
        map.put("London", new Date("OCTOBER 20 1885"));
        map.put("Navalny", new Date("JANUARY 07 1970"));
        map.put("Petrov", new Date("MAY 14 1999"));
        map.put("Sidorov", new Date("AUGUST 08 1998"));
        map.put("Volkov", new Date("OCTOBER 12 1980"));
        //напишите тут ваш код

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Date> pair = iterator.next();
            Date date = pair.getValue();
            int month = date.getMonth;
            if ((month >= 5) && (month <=7))
                iterator.remove();
        }
    }*/
}