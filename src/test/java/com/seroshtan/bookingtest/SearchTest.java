package com.seroshtan.bookingtest;

import com.seroshtan.bookingtest.pages.MainPage;
import com.seroshtan.bookingtest.pages.SearchResultsPage;
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

    private static final String SEARCH_PLACE = "New York City";
    private static final LocalDate FROM_DATE = LocalDate.of(2016, Month.JUNE, 1);
    private static final LocalDate TO_DATE = LocalDate.of(2016, Month.JUNE, 5);

    @Managed
    WebDriver driver;

    private MainPage mainPage;
    private SearchResultsPage resultsPage;

    @Test
    public void searchFromTheMainPage() {
        mainPage.open();
        mainPage.searchFor(SEARCH_PLACE, FROM_DATE, TO_DATE);
        List<String> locations = resultsPage.readHotelLocations();
        System.out.println("Number of hotels: " + locations.size());
        locations.stream().forEach(location ->
                assertTrue("Hotel isn't located in " + SEARCH_PLACE,
                        location.contains(SEARCH_PLACE)));
    }
}
