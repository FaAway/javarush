package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
       /* FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[1]);
        byte[] buf = new byte[in.available()];
        Charset windows = Charset.forName("Windows-1251");
        Charset utf = Charset.forName("UTF-8");
        in.read(buf);
        String s = new String(buf, "UTF-8");
        out.write(s.getBytes("Windows-1251"));
        in.close();
        out.close();*/

        FileInputStream inputStream=new FileInputStream(args[0]);
        FileOutputStream outputStream=new FileOutputStream(args[1]);
        byte[] buff=new byte[inputStream.available()];
        inputStream.read(buff);
        String s= new String(buff, "UTF-8");
        outputStream.write(s.getBytes("Windows-1251"));
        inputStream.close();
        outputStream.close();
    }
}
