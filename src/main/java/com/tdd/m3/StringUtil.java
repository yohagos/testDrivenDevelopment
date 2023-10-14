package com.tdd.m3;

public class StringUtil {

    public static String truncate(String input, int limit) {
        if (input == null)
            throw new IllegalArgumentException("Input cannot be null");
        if (limit < 1)
            throw new IllegalArgumentException("limit smaller then 1");
        if (input.length() <= limit)
            return input;
        return input.substring(0, limit) + "...";
    }
}
