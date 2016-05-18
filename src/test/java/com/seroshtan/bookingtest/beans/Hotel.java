package com.seroshtan.bookingtest.beans;

/**
 * This bean represents hotel entity from booking.com web-site.
 *
 * It's used for convenient comparison of expected and actual hotel values in tests.
 *
 * @author Julia Siroshtan
 */
public final class Hotel {
    private final String name;
    private final String location;

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
