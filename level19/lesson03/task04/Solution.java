package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner scanner;
        public PersonScannerAdapter(Scanner sc) {
            scanner = sc;
        }

        @Override
        public Person read() throws IOException {
            //if (!scanner.hasNext()) return new Person(null, null, null, null);
            String[] values = scanner.nextLine().split(" ");
            GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(values[5]), Integer.parseInt(values[4]) - 1, Integer.parseInt(values[3]));
            Person person = new Person(values[1], values[2], values[0], calendar.getTime());
            return person;
        }

        @Override
        public void close() throws IOException {
            scanner.close();
        }
    }
}
