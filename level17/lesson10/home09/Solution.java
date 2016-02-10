package com.javarush.test.level17.lesson10.home09;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            reader.close();

            String s;
            BufferedReader fileReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1)));
            while ((s = fileReader1.readLine()) != null) {
                allLines.add(s);
            }
            BufferedReader fileReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName2)));
            while ((s = fileReader2.readLine()) != null) {
                forRemoveLines.add(s);
            }

            fileReader1.close();
            fileReader2.close();

            new Solution().joinData();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (CorruptedDataException e) {
            System.out.println("CorruptedDataException");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            List<String> tempList = new ArrayList<String>();
            tempList.addAll(allLines);
            tempList.removeAll(forRemoveLines);
            allLines = tempList;
            return;
        }

        for (String s: forRemoveLines) {
            if (!allLines.contains(s)) {
                List<String> tempList = new ArrayList<String>();
                allLines = tempList;
                throw new CorruptedDataException();
            }
        }
    }
}
