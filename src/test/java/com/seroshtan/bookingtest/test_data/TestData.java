package com.seroshtan.bookingtest.test_data;

import com.seroshtan.bookingtest.enums.Filter;

import java.time.LocalDate;
import java.time.Month;

public class TestData {
    public static final String CITY_PARIS = "Paris";
    public static final String CITY_NYC = "New York";
    public static final LocalDate FROM_DATE = LocalDate.of(2022, Month.JUNE, 20);
    public static final LocalDate TO_DATE = LocalDate.of(2022, Month.JUNE, 29);
    public static final Filter.Rating TEST_RATING = Filter.Rating.THREE_STARS;
}
