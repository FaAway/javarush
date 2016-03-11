package com.javarush.test.level30.lesson02.home01;

import java.math.BigInteger;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {

    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) throws NumberFormatException {
       /* StringBuilder expectedDigit = new StringBuilder();
        int base = number.getNumerationSystem().getNumerationSystemIntValue();
        long tempValue = Long.parseLong(number.getDigit(), base);
        int expectedBase = expectedNumerationSystem.getNumerationSystemIntValue();
        if (tempValue == 0)
            expectedDigit.append("0");
        else while (tempValue > 0) {
            expectedDigit.append(NumerationSystemType.numerics[(int)(tempValue % expectedBase)]);
            tempValue = tempValue / expectedBase;
        }
        return new Number(expectedNumerationSystem, expectedDigit.reverse().toString());*/

        // 2nd way:
        BigInteger a = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());
        return new Number(expectedNumerationSystem, a.toString(expectedNumerationSystem.getNumerationSystemIntValue()));
    }
}
