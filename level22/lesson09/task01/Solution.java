package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        ArrayList<String> words = new ArrayList<>();
        try (FileReader f = new FileReader(filename);
             BufferedReader r = new BufferedReader(f)) {
            while (r.ready()) {
                String[] words_ = r.readLine().split(" ");
                for (String word : words_) {
                    words.add(word);
                }
            }
        }

        for (int i = 0; i < words.size(); i++)
        {
            for (int j = 0; j < words.size(); )
            {
                if (words.get(j).equals(new StringBuilder(words.get(i)).reverse().toString()) && j != i)
                {
                    Pair pair = new Pair(words.get(j), words.get(i));
                    result.add(pair);
                    words.remove(j);
                    words.remove(i);
                    j = 0;
                } else
                    j++;
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
