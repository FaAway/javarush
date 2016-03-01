package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FarAway on 28.02.2016.
 */
public class DirectorTablet {
    public void printAdvertisementProfit(){
        TreeMap<Date, Long> advertisementRevenueAgregatedByDay = StatisticEventManager.getInstance().getAdvertisementRevenueAgregatedByDay();
        if (advertisementRevenueAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = advertisementRevenueAgregatedByDay.descendingKeySet();
        Long totalAmmout = Long.valueOf(0);
        for (Date date : datesRow) {
            Long dayAmount = advertisementRevenueAgregatedByDay.get(date);
            totalAmmout += dayAmount;
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f",
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date),
                    0.01d * dayAmount)
            );
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", 0.01d * totalAmmout));
    }
    public void printCookWorkloading() {
        TreeMap<Date, HashMap<String, Integer>> cookWorkloadingAgregatedByDay = StatisticEventManager.getInstance().getCookWorkloadingAgregatedByDay();
        if (cookWorkloadingAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = cookWorkloadingAgregatedByDay.descendingKeySet();
        for (Date date: datesRow) {
            ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date));
            List<Map.Entry<String, Integer>> cooksNameWorkDuration = new ArrayList(cookWorkloadingAgregatedByDay.get(date).entrySet());
            Collections.sort(cooksNameWorkDuration, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });
            for (Map.Entry<String, Integer> cookNameWorkDuration: cooksNameWorkDuration) {
                ConsoleHelper.writeMessage(String.format("%s - %d min",
                        cookNameWorkDuration.getKey(),
                        (int)Math.ceil(cookNameWorkDuration.getValue() / 60.0))
                );
            }
        }
    }
    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getActiveAdvertisements();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(String.format("%s - %d",
                    advertisement.getName(),
                    advertisement.getHits()));
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getNonActiveAdvertisements();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
