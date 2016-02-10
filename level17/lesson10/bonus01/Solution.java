package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //Проверка на число параметров
        if (args.length < 2 || args.length > 7 || args.length == 3)
            return;
        else processParams(args);
        /*String[] params1 = {"-u", "1", "Петрова Мария", "ж", "01/12/1992"};
        processParams(params1);
        String[] params2 = {"-i", "2"};
        processParams(params2);*/
    }

    public static void processParams(String[] args) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String name = "";
        Date bd = null;
        int id;
        switch (args[0]) {
            case "-c":
                name = args[1];
                if (!args[2].equals("м") && !args[2].equals("ж")) {
                    name = name + " " + args[2];
                }

                try {
                    bd = format1.parse(args[args.length - 1]);
                } catch (ParseException e) {
                    System.out.println("ParseException: maybe wrong date format");
                    return;
                }
                allPeople.add(args[args.length - 2].equals("м") ? Person.createMale(name, bd)
                        : Person.createFemale(name, bd));
                System.out.println(allPeople.size() - 1);
                break;
            case "-u":
                id = Integer.parseInt(args[1]);
                name = args[2];
                if (!args[3].equals("м") && !args[3].equals("ж")) {
                    name = name + " " + args[3];
                }

                try {
                    bd = format1.parse(args[args.length - 1]);
                } catch (ParseException e) {
                    System.out.println("ParseException: maybe wrong date format");
                    return;
                }

                allPeople.get(id).setSex(args[args.length - 2].equals("м") ? Sex.MALE : Sex.FEMALE);
                allPeople.get(id).setName(name);
                allPeople.get(id).setBirthDay(bd);
                break;
            case "-d":
                id = Integer.parseInt(args[1]);
                allPeople.get(id).setBirthDay(null);
                allPeople.get(id).setName(null);
                allPeople.get(id).setSex(null);
                break;
            case "-i":
                id = Integer.parseInt(args[1]);
                System.out.println(allPeople.get(id).getName() + (allPeople.get(id).getSex() == Sex.MALE ? " м " : " ж ") + format2.format(allPeople.get(id).getBirthDay()));
                break;
        }
    }
}
