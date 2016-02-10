package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.*;
import java.util.Arrays;

public class FileConsoleWriter extends FileWriter{

    public FileConsoleWriter(String fileName) throws IOException {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        super(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        super(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        super(fd);
    }

    private char[] buff;
    private final int buffSize = 1024;

    @Override
    public void write(int c) throws IOException {
        super.write(c);
        System.out.print(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        write(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        System.out.println(String.copyValueOf(cbuf).substring(off, off + len));
        super.write(cbuf, off, len);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        char cbuf[];
        if (len <= buffSize)
        {
            if (buff == null)
            {
                buff = new char[buffSize];
            }
            cbuf = buff;
        } else
        {
            cbuf = new char[len];
        }
        str.getChars(off, (off + len), cbuf, 0);
        write(cbuf, 0, len);
    }
}
