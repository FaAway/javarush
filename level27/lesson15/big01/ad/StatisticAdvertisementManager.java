package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarAway on 29.02.2016.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    
    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }
    
    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveAdvertisements() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0)
                result.add(advertisement);
        }
        return result;
    }

    public List<Advertisement> getNonActiveAdvertisements() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() == 0)
                result.add(advertisement);
        }
        return result;
    }
}
