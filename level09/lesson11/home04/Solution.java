package com.javarush.test.level09.lesson11.home04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* Конвертер дат
Ввести с клавиатуры дату в формате «08/18/2013»
Вывести на экран эту дату в виде «AUG 18, 2013».
Воспользоваться объектом Date и SimpleDateFormat.
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        Date date = new Date(Date.parse(reader.readLine()));
        //Date date = dateFormat.parse(reader.readLine());

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        System.out.println(dateFormat1.format(date).toUpperCase());
    }
}
