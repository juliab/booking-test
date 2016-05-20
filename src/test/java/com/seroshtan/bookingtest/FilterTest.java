package com.seroshtan.bookingtest;

import com.seroshtan.bookingtest.pages.MainPage;
import com.seroshtan.bookingtest.pages.SearchResultsPage;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static com.seroshtan.bookingtest.beans.Filter.*;

/**
 * Test class for hotels filtering on the results page.
 *
 * @author Julia Siroshtan
 */
@RunWith(SerenityRunner.class)
public class FilterTest {

    private static final String SEARCH_PLACE = "Paris";
    private static final Rating TEST_RATING = Rating.THREE_STARS;

    @Managed
    WebDriver driver;

    private MainPage mainPage;
    private SearchResultsPage resultsPage;

    @Before
    public void goToResultsPage() {
        mainPage.open();
        mainPage.searchFor(SEARCH_PLACE);
    }

    @Test
    public void filterByRating() {
        resultsPage.filterHotelsBy(TEST_RATING);
        List<Integer> hotelRatings = resultsPage.readHotelRatings();
        hotelRatings.stream().forEach(rating ->
                assertEquals("Filter by hotel rating didn't work",
                        rating, TEST_RATING.getNumberOfStars()));
    }
}
