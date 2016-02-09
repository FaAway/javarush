package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        //Проверка на число параметров
        if (args.length < 2 || args.length == 3)
            return;
        else processParams(args);

        //Tests
        /*String[] params1 = {"-u", "1", "Петрова Мария", "ж", "01/12/1992"};
        processParams(params1);
        String[] params2 = {"-i", "1"};
        processParams(params2);
        String[] params3 = "-c Михаил м 12/12/1988 Юрий Иванович м 03/05/1965 Анна ж 01/01/1992".split(" ");
        processParams(params3);
        String[] params4 = "-i 2 3 4".split(" ");
        processParams(params4);*/
    }

    public static void processParams(String[] args) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String name = "";
        Date bd;
        int id, i = 1;
        try {
            switch (args[0]) {
                case "-c":
                    do {
                        name = args[i];
                        i++;
                        for (int j = 0; j < 2; j++) {
                            if (!args[i].equals("м") && !args[i].equals("ж")) {
                                name = name + " " + args[i];
                                i++;
                            } else break;
                        }
                        bd = format1.parse(args[i+1]);
                        synchronized (allPeople) {
                            allPeople.add(args[i].equals("м") ? Person.createMale(name, bd)
                                    : Person.createFemale(name, bd));
                            i += 2;
                            System.out.println(allPeople.size() - 1);
                        }
                    } while (i < args.length);

                    break;
                case "-u":
                    synchronized (allPeople) {
                        do {
                            id = Integer.parseInt(args[i]);
                            i++;
                            name = args[i];
                            i++;
                            for (int j = 0; j < 2; j++) {
                                if (!args[i].equals("м") && !args[i].equals("ж")) {
                                    name = name + " " + args[i];
                                    i++;
                                } else break;
                            }
                            bd = format1.parse(args[i + 1]);

                            Person updPerson = allPeople.get(id);
                            updPerson.setSex(args[args.length - 2].equals("м") ? Sex.MALE : Sex.FEMALE);
                            updPerson.setName(name);
                            updPerson.setBirthDay(bd);
                            i += 2;
                        } while (i < args.length);
                    }
                    break;
                case "-d":
                    do {
                        synchronized (allPeople) {
                            id = Integer.parseInt(args[i]);
                            allPeople.get(id).setBirthDay(null);
                            allPeople.get(id).setName(null);
                            allPeople.get(id).setSex(null);
                            i++;
                        }
                    } while (i < args.length);
                    break;
                case "-i":
                    do {
                        id = Integer.parseInt(args[i]);
                        Person iPerson = allPeople.get(id);
                        System.out.println(iPerson.getName() + (iPerson.getSex() == Sex.MALE ? " м " : " ж ") + format2.format(iPerson.getBirthDay()));
                        i++;
                    } while (i < args.length);
                    break;
            }
        } catch (ParseException e) {
            System.out.println("ParseException: maybe wrong date format");
        }
    }
}
