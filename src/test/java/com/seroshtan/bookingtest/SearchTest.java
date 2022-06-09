package com.seroshtan.bookingtest;

import com.seroshtan.bookingtest.pages.MainPage;
import com.seroshtan.bookingtest.pages.SearchResultsPage;
import com.seroshtan.bookingtest.test_data.TestData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Test class for hotel search.
 *
 * @author Julia Siroshtan
 */
@RunWith(SerenityRunner.class)
public class SearchTest {

    private static final String TEST_CITY = TestData.CITY_NYC;

    @Managed
    WebDriver driver;

    private MainPage mainPage;
    private SearchResultsPage resultsPage;

    @Test
    public void searchFromTheMainPage() {
        mainPage.open();
        mainPage.searchFor(TEST_CITY, TestData.FROM_DATE, TestData.TO_DATE);
        List<String> locations = resultsPage.readHotelLocations();
        locations.forEach(location ->
                assertTrue("Hotel (" + location + ") isn't located in " + TEST_CITY,
                        location.contains(TEST_CITY)));
    }
}
