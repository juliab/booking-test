package com.seroshtan.bookingtest.util;

/**
 * This is a helper class that contains methods operating on strings.
 *
 * @author Julia Siroshtan
 */
public class StringUtil {

    /**
     * Convert string value to integer.
     *
     * This method removes all characters except integers from string.
     *
     * @param string String value.
     * @return Integer.
     */
    public static Integer intFromString(String string) {
        return Integer.parseInt(string.replaceAll("[\\D]", ""));
    }
}
