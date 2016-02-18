package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {
/*
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("name", "Ivanov");
        params.put("country", "Ukraine");
        params.put("city", "Kiev");
        params.put("age", null);
        StringBuilder sb = getCondition(params);
        System.out.println(sb.toString());
    }
*/

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params.isEmpty()) return sb;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null)
                sb.append(String.format("%s = '%s' and ", entry.getKey(), entry.getValue()));
        }
        sb.delete(sb.length() - 5, sb.length());
        return sb;
    }
}
