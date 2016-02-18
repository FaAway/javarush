package com.javarush.test.level21.lesson16.big01;

/**
 * Created by FarAway on 14.02.2016.
 */
public class Horse {
    private String name;
    private double speed;
    private double distance;

    public void move() {
        distance += speed * Math.random();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.round(distance); i++) {
            sb.append(".");
        }
        sb.append(name);
        System.out.print(sb.toString());
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }


}
