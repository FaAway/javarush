package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarAway on 08.03.2016.
 */
public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers) throws IllegalArgumentException{
        this.view = view;
        this.providers = providers;
        if (view == null || providers == null || providers.length == 0) throw new IllegalArgumentException();
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        for (Provider provider : providers) {
            try {
                vacancies.addAll(provider.getJavaVacancies(city));
            } catch (NullPointerException e) {

            }
        }
        view.update(vacancies);
    }
}
