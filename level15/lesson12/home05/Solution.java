package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    private Solution(boolean a) {

    }

    private Solution(boolean a, boolean b) {

    }

    private Solution(boolean a, float b) {

    }

    Solution(short a) {

    }

    Solution(String s) {

    }

    Solution(String s1, String s2) {

    }

    protected Solution(int a) {

    }

    protected Solution(int a, int b) {

    }

    protected Solution(int a, int b, int c) {

    }

    public Solution() {

    }

    public Solution(short a, short b) {

    }

    public Solution(short a, short b, short c) {

    }
}

