package com.javarush.test.level21.lesson05.task03;

import java.io.*;
import java.util.Date;

/* Ошибка в equals/hashCode
Исправьте ошибки реализаций методов equals и hashCode для класса Solution
*/
public class MySolution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private MySolution solution;

    public MySolution(int anInt, String string, double aDouble, Date date, MySolution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof MySolution)) return false;

        MySolution solution1 = (MySolution) o;

        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
        if (anInt != solution1.anInt) return false;
        if (date != null ? !date.equals(solution1.date) : solution1.date != null) return false;
        if (solution != null ? !solution.equals(solution1.solution) : solution1.solution != null) return false;
        if (string != null ? !string.equals(solution1.string) : solution1.string != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

/*   public static void main(String[] args) throws Exception{
        Solution solution = new Solution(5, "String", 6.0d, new Date(2015, 01, 13), null);
        Solution solutionWrapper = new Solution(7, "StringOfWrapper", 8.0d, new Date(2015,01,14), solution);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(solutionWrapper);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
        Solution loadedSolution = (Solution)ois.readObject();
        System.out.println(solutionWrapper.equals(loadedSolution));
    }*/
}
