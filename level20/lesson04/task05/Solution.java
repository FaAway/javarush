package com.javarush.test.level20.lesson04.task05;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* Как сериализовать что-то свое?
Сделайте так, чтобы сериализация класса Object была возможной
*/
public class Solution {
    public static class Object implements Serializable{
        public String string1;
        public String string2;

        private void readObject(ObjectInputStream stream)
                throws IOException, ClassNotFoundException {
            int tmp = countStrings;
            countStrings = stream.readInt() - 1;
            string1 = new String();
            countStrings = stream.readInt() - 1;
            string2 = new String();
            countStrings = tmp;
        }

        private void writeObject(ObjectOutputStream stream)
                throws IOException {
            stream.writeInt(string1.number);
            stream.writeInt(string2.number);
        }
    }

    public static int countStrings;

    public static class String implements Serializable {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }
}
