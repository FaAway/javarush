package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {

        //String testString = "Lion.avi.part3\nLion.avi.part2\nPumba.avi.part2\nLion.avi.part4\nLion.avi.part1\nPumba.avi.part1\nend";
        //System.setIn(new ByteArrayInputStream(testString.getBytes()));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        TreeSet<String> set = new TreeSet<>();

        for (s = reader.readLine(); !s.equals("end"); s = reader.readLine())
            set.add(s);

        reader.close();

        String currentFileName = "", nextName;
        FileOutputStream out = null;
        Iterator<String> iterator =  set.iterator();
        while (iterator.hasNext()) {
            nextName = iterator.next();
            if (!nextName.contains(currentFileName) || currentFileName.equals("")) {
                if (out != null) out.close();
                currentFileName = nextName.substring(0, nextName.lastIndexOf('.'));
                out = new FileOutputStream(currentFileName);
            }
            FileInputStream in = new FileInputStream(nextName);
            byte[] buf = new byte[in.available()];
            while (in.available() > 0) {
                int readed = in.read(buf);
                out.write(buf, 0, readed);
            }
            in.close();
        }
        if (out != null) out.close();
    }
}
