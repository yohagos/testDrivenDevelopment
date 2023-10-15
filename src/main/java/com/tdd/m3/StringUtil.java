package com.tdd.m3;

public class StringUtil {

    public static String truncate(String input, int limit, String cutOffChars) {
        if (input == null)
            throw new IllegalArgumentException("Input cannot be null");
        if (limit < 1)
            throw new IllegalArgumentException("limit smaller then 1");
        if (checkInputLengthShorterLimitOrEllipsisLength(input, limit, cutOffChars))
            return input;
        return input.substring(0, limit) + cutOffChars;
    }

    public static String truncateWithEllipsis(String input, int limit) {
        return truncate(input, limit, "...");
    }

    private static boolean checkInputLengthShorterLimitOrEllipsisLength(String input, int limit, String ellipsis) {
        return input.length() <= limit || input.length() <= ellipsis.length();
    }
}
