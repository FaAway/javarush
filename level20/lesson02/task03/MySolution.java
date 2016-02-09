package com.javarush.test.level20.lesson02.task03;

//Known problem: need to use latin1 code page instead of UTF8

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class MySolution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception{
        load(new FileInputStream("input.txt"));
        save(new FileOutputStream("input.txt"));
    }

    public static void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        String fileName;
        Scanner sc = new Scanner(System.in);
        fileName = sc.nextLine();
        sc.close();

        try {
            load(new FileInputStream(fileName));
        } catch (PropertyParseException e) {
            System.out.println(e.toString() + " At index = " + e.getErrorOffset());
        } catch (IOException e ) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        PrintWriter writer = new PrintWriter(outputStream);
        for (Map.Entry<String, String> stringStringEntry : properties.entrySet()) {
            StringBuilder key = new StringBuilder(stringStringEntry.getKey());
            StringBuilder value = new StringBuilder(stringStringEntry.getValue());
            //adding escape characters if needed
            for (int i = 0; i < key.length(); i++) {
                if ("#! =:".indexOf(key.charAt(i)) != -1)
                    if (i == 0 || key.charAt(i-1) != '\\')
                        key.insert(i, '\\');
            }
            //adding escape characters if needed (don't process SPACE characters)
            for (int i = 0; i < value.length(); i++) {
                if ("#!=:".indexOf(value.charAt(i)) != -1)
                    if (i == 0 || value.charAt(i-1) != '\\')
                        value.insert(i, '\\');
            }
            writer.println(key + "=" + value);
        }
        writer.close();
    }

    public static void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String s = null;
        boolean multiLine = false;
        while (reader.ready()) {
            if (multiLine) {
                s = s.substring(0, s.length() - 1) + reader.readLine();
                multiLine = false;
            } else s = reader.readLine();
            if (s.isEmpty()) continue;                     //skip empty lines
            if ("#!".indexOf(s.charAt(0)) != -1) continue; //skip comments
            if (s.charAt(s.length() - 1) == '\\') {
                multiLine = true;
                continue;
            }
            if (s.isEmpty()) continue;
            Pair<String, String> pairKeyValue = getKeyValueProperty(s);
            properties.put(pairKeyValue.getKey(), pairKeyValue.getValue());
        }
        reader.close();
    }

    static class PropertyParseException extends ParseException {
        public PropertyParseException(String s, int errorOffset) {
            super(s, errorOffset);
        }
    }

    public static Pair<String, String> getKeyValueProperty(String param) throws PropertyParseException{
        StringBuilder key = new StringBuilder(),
                value = new StringBuilder();
        if (param == null || param.length() < 1) throw new PropertyParseException("No key or value found.", 0);

        int index = 0;
        boolean flagSpecialChar = false;
        //getting for key
        for (int i = 0; i < param.length() && index == 0; i++) {
            switch (param.charAt(i)) {
                case '\\':
                    flagSpecialChar = true;
                    break;
                case ':':
                case ' ':
                case '=':
                    if (!flagSpecialChar) {
                        index = i + 1;
                        break; // goto next phase
                    } else {
                        key.append(param.charAt(i));
                        flagSpecialChar = false;
                    }
                    break;
                default:
                    key.append(param.charAt(i));
                    flagSpecialChar = false;
                    break;
            }
        }

        //checking & skipping (key <-> value) delimiter
        while (": =".indexOf(param.charAt(index)) != -1 && index < param.length())
            index++;
        if (index == param.length()) throw new PropertyParseException("Can't find value.", index);

        //getting value
        for (int i = index; i < param.length(); i++) {
            switch (param.charAt(i)) {
                case '\\':
                    flagSpecialChar = true;
                    if (i == param.length() - 1) throw new PropertyParseException("Part of value missed.", i);
                    break;
                /* Убрана строгая проверка значений на экранирование служебных символов как в java.util.Properties
                case ':':
                case ' ':
                case '=':
                    if (!flagSpecialChar) {
                        throw new PropertyParseException("Excess key <-> value delimiter.", i);
                    } else {
                        value.append(param.charAt(i));
                        flagSpecialChar = false;
                    }
                    break;
                case '!':
                case '#':
                    if (!flagSpecialChar) {
                        i = param.length() - 1; //skip comment
                    } else {
                        value.append(param.charAt(i));
                        flagSpecialChar = false;
                    }
                    break;*/
                case '\r': if (!flagSpecialChar) throw new PropertyParseException("Illegal symbol: CARRIAGE RETURN. Must be after ESCAPE symbol.", i);
                    break; //skipping CARRIAGE RETURN
                case '\n': if (!flagSpecialChar) throw new PropertyParseException("Illegal symbol: LINE FEED. Must be after ESCAPE [and CARRIAGE RETURN] symbol.", i);
                    flagSpecialChar = false;
                    break; //skipping LINE FEED
                default:
                    value.append(param.charAt(i));
                    flagSpecialChar = false;
                    break;
            }
        }
        return new Pair<>(key.toString(), value.toString());
    }

    public static class Pair<K, V> {
        private K key;
        private V value;

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public Pair(K var1, V var2) {
            this.key = var1;
            this.value = var2;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        public int hashCode() {
            return this.key.hashCode() * 13 + (this.value == null?0:this.value.hashCode());
        }

        public boolean equals(Object var1) {
            if(this == var1) {
                return true;
            } else if(!(var1 instanceof Pair)) {
                return false;
            } else {
                Pair var2 = (Pair)var1;
                if(this.key != null) {
                    if(!this.key.equals(var2.key)) {
                        return false;
                    }
                } else if(var2.key != null) {
                    return false;
                }

                if(this.value != null) {
                    if(!this.value.equals(var2.value)) {
                        return false;
                    }
                } else if(var2.value != null) {
                    return false;
                }

                return true;
            }
        }
    }
}
