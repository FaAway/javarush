package com.javarush.test.level22.lesson13.task03;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {

    public static void main(String[] args) {
        String test1 = "+380501234567";
        System.out.println("Test1: " + checkTelNumber(test1));
        String test2 = "+38(050)1234567";
        System.out.println("Test2: " + checkTelNumber(test2));
        String test3 = "+38(050)123-4567";
        System.out.println("Test3: " + checkTelNumber(test3));
        String test4 = "+38(050)123-45-67";
        System.out.println("Test4: " + checkTelNumber(test4));


        String test5 = "(050)123-45-67";
        System.out.println("Test5: " + checkTelNumber(test5));
        String test6 = "0501234567";
        System.out.println("Test6: " + checkTelNumber(test6));

        String test7 = "+38(050)123-45-6-7";
        System.out.println("Test7 must be false: " + checkTelNumber(test7));
        String test8 = "+38)050(1234567";
        System.out.println("Test8 must be false: " + checkTelNumber(test8));
        String test9 = "050ххх4567";
        System.out.println("Test9 must be false: " + checkTelNumber(test9));
        String test10 = "050123456";
        System.out.println("Test10 must be false: " + checkTelNumber(test10));
        String test11 = "(0)501234567";
        System.out.println("Test11 must be false: " + checkTelNumber(test11));
    }

    public static boolean checkTelNumber(String telNumber) {
        if (telNumber.charAt(0) == '+') {
            boolean bRightDigitsCount = (telNumber.length() - 12) == telNumber.replaceAll("\\d", "").length();
            //System.out.println("digit count: " + bRightDigitsCount);
            return bRightDigitsCount && telNumber.matches("\\+(\\d{12}|\\d{1,2}\\(\\d{3}\\)(\\d{7,8}|\\d+-\\d+|\\d+-\\d+-\\d+))");
        }
        else {
            boolean bRightDigitsCount = (telNumber.length() - 10) == telNumber.replaceAll("\\d", "").length();
            return bRightDigitsCount && telNumber.matches("(\\d{10}|\\(\\d{3}\\)(\\d{7}|\\d+-\\d+|\\d+-\\d+-\\d+))");
        }
    }
}
