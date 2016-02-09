package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;

public class Solution {
    private static final int key = 123;
    public static void main(String[] args) throws FileNotFoundException, IOException{
        if (args.length != 3) return;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(args[1]));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(args[2]));
        while (in.available() > 0)
            out.write(in.read() ^ key );
        in.close();
        out.close();
/*        if (args[0].equals("-e")) {

            }
        } else if (args[0].equals("-d")) {

        }*/
    }

}
