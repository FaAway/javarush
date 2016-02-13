package com.javarush.test.level21.lesson05.task01;

import java.util.HashSet;
import java.util.Set;

/* Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Solution n) {
        if (n == this) return true;
        if (n == null || getClass() != n.getClass()) return false;
        boolean firstPart = (first != null) ? first.equals(n.first) : n.first == null;
        boolean secondPart = (last != null) ? last.equals(n.last) : n.last == null;
        return firstPart && secondPart;
    }

    public int hashCode() {
        int firstHashCode = (first == null) ? 0 : first.hashCode();
        int lastHashCode = (last == null) ? 0 : last.hashCode();
        return 31 * firstHashCode + lastHashCode;
    }

    @Override
    public boolean equals(Object obj) {
        Solution n = (Solution) obj;
        if (n == this) return true;
        if (n == null || getClass() != n.getClass()) return false;
        boolean firstPart = (first != null) ? first.equals(n.first) : n.first == null;
        boolean secondPart = (last != null) ? last.equals(n.last) : n.last == null;
        return firstPart && secondPart;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
