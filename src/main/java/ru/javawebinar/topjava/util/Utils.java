package ru.javawebinar.topjava.util;

public class Utils {

    public static <T> T nvl(T o, T alternativ){
        return (o == null)?alternativ:o;
    }
}
