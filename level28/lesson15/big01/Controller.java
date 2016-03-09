package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Model;

/**
 * Created by FarAway on 07.03.2016.
 */
public class Controller {

    private Model model;

    private Controller() {}

    public Controller(Model model) throws IllegalArgumentException{
        this.model = model;
        if (model == null) throw new IllegalArgumentException();
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
}
