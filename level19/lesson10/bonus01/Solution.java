package com.javarush.test.level19.lesson10.bonus01;

import java.io.*;
import java.util.*;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
               строка0            ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
               строка5            ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();
    static boolean test = false;

    public static void main(String[] args) throws IOException {
        //init
        BufferedReader f1;
        BufferedReader f2;
        if (!test) {
            Scanner sc = new Scanner(System.in);
            f1 = new BufferedReader(new FileReader(sc.nextLine()));
            f2 = new BufferedReader(new FileReader(sc.nextLine()));
            sc.close();
        } else {
            f1 = new BufferedReader(new FileReader("input.txt"));
            f2 = new BufferedReader(new FileReader("input2.txt"));
        }
        //read
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        while (f1.ready()) {
            l1.add(f1.readLine());
        }
        while (f2.ready()) {
            l2.add(f2.readLine());
        }

        //process
        int j = 0;
        for (int i = 0; i < l1.size(); i++){
            if (j >= l2.size()) {
                lines.add(new LineItem(Type.REMOVED, l1.get(i)));
                if (test) System.out.println("REMOVED "+ l1.get(i));
                break;
            } else
            if (l1.get(i).equals(l2.get(j))) {
                lines.add(new LineItem(Type.SAME, l1.get(i)));
                if (test) System.out.println("SAME "+ l1.get(i));
                j++;
            } else
            if (j < (l2.size() - 1) && l1.get(i).equals(l2.get(j + 1))) {
                lines.add(new LineItem(Type.ADDED, l2.get(j)));
                if (test) System.out.println("ADDED "+ l2.get(j));
                j++;
                i--;
            } else
            if (i < (l1.size() - 1) && l1.get(i + 1).equals(l2.get(j))) {
                lines.add(new LineItem(Type.REMOVED, l1.get(i)));
                if (test) System.out.println("REMOVED "+ l1.get(i));
            }
        }
        if (lines.get(lines.size() - 1).type == Type.SAME && j <= (l2.size() - 1) ) {
            lines.add(new LineItem(Type.ADDED, l2.get(j)));
            if (test) System.out.println("ADDED " + l2.get(j));
        }

        //close files
        f1.close();
        f2.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
