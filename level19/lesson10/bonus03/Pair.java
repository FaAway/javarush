package com.javarush.test.level19.lesson10.bonus03;

/**
 * Created by FarAway on 07.02.2016.
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public Pair(K var1, V var2) {
        this.key = var1;
        this.value = var2;
    }
}
