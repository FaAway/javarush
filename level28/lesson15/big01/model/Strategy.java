package com.javarush.test.level28.lesson15.big01.model;

import java.util.List;

/**
 * Created by FarAway on 07.03.2016.
 */
public interface Strategy<T> {
    List<T> getVacancies(String searchString);
}
