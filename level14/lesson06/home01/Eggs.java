package com.javarush.test.level14.lesson06.home01;

import java.util.HashMap;

/**
 * Created by FarAway on 16.01.2016.
 */
public interface Eggs {
    HashMap<String, Integer> eggsPerMonthByCountry = new HashMap<String, Integer>() {{
        put("Ukraine", 400);
        put("Russia", 350);
        put("Moldova", 290);
        put("Belarus", 500);
    }};
}
