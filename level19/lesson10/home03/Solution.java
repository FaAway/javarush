package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static class PersonScannerAdapter {
        private BufferedReader reader;
        public PersonScannerAdapter(BufferedReader reader) {
            this.reader = reader;
        }

        public Person read(String line) throws IOException {
            //if (!scanner.hasNext()) return new Person(null, null, null, null);
            String[] values = line.split(" ");
            String name = values[0];
            for (int i = 1; i < values.length - 3; i++) {
                name += " " + values[i];
            }
            GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(values[values.length - 1]), Integer.parseInt(values[values.length - 2]) - 1, Integer.parseInt(values[values.length - 3]));
            Person person = new Person(name, calendar.getTime());
            return person;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        PersonScannerAdapter ps = new PersonScannerAdapter(reader);
        String s;
        while ((s = reader.readLine()) != null)
            PEOPLE.add(ps.read(s));
        reader.close();
    }

}
