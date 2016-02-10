package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    private static String tag;
    private static String tagBegin;
    private static String tagEnd;
    public static void main(String[] args) throws IOException{
        tag = args[0];
        tagBegin = "<" + tag;
        tagEnd = "</" + tag + ">";
        Scanner sc =new Scanner(System.in);

        BufferedReader reader = new BufferedReader(new FileReader(sc.nextLine()));
        sc.close();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();

        TreeMap<Integer, Integer> indexMap = (TreeMap)separateTags(getOpenCloseIndexes(sb.toString()));
        for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
            System.out.println(sb.substring(entry.getKey(), entry.getValue()));
        }
    }

    //fill in array of tag positions (String indexes)
    //Input: Text
    //Output: array of pairs <open or close tag, index of start or end in text>
    public static ArrayList<Pair<Boolean, Integer>> getOpenCloseIndexes(String sb) {
        ArrayList<Pair<Boolean, Integer>> openCloseIndexes = new ArrayList<>();
        int seekPos = 0;
        while (sb.indexOf(tag, seekPos) != -1) {
            int nextTagBeginPos = sb.indexOf(tagBegin, seekPos);
            int nextTagEndPos = sb.indexOf(tagEnd, seekPos);
            if (nextTagEndPos == -1) break;
            if (nextTagBeginPos < nextTagEndPos && nextTagBeginPos != -1) {
                openCloseIndexes.add(new Pair(true, nextTagBeginPos));
                seekPos = nextTagBeginPos + tagBegin.length();
            }
            else {
                openCloseIndexes.add(new Pair(false, nextTagEndPos + tagEnd.length()));
                seekPos = nextTagEndPos + tagEnd.length();
            }
        }
        return openCloseIndexes;
    }

    //Find pairs
    //Input: array of pairs <open or close tag, index of start or end in text>
    //      If open -> index of 1st symbol of start tag
    //      if close -> index of last index of end tag
    //Output: <index of tag start, index tag end> (index of chars in String)
    public static Map<Integer, Integer> separateTags(ArrayList<Pair<Boolean, Integer>> openCloseIndexes) {
        Map<Integer, Integer> indexMap = new TreeMap<Integer, Integer>();
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int current = 0;
        while (current < openCloseIndexes.size()) {
            if (openCloseIndexes.get(current).getKey() == true) {
                stack.push(current);
                current++;
                continue;
            }
            indexMap.put(openCloseIndexes.get(stack.pop()).getValue(), openCloseIndexes.get(current).getValue());
            current++;
        }
        return indexMap;
    }
}
