package com.seroshtan.bookingtest;

import com.seroshtan.bookingtest.pages.MainPage;
import com.seroshtan.bookingtest.pages.SearchResultsPage;
import com.seroshtan.bookingtest.test_data.TestData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for hotels filtering on the results page.
 *
 * @author Julia Siroshtan
 */
@RunWith(SerenityRunner.class)
public class FilterTest {

    @Managed
    WebDriver driver;

    private MainPage mainPage;
    private SearchResultsPage resultsPage;

    @Before
    public void goToResultsPage() {
        mainPage.open();
        mainPage.searchFor(TestData.CITY_PARIS, TestData.FROM_DATE, TestData.TO_DATE);
    }

    @Test
    public void filterByRating() {
        resultsPage.filterHotelsBy(TestData.TEST_RATING);
        List<Integer> hotelRatings = resultsPage.readHotelRatings();
        hotelRatings.forEach(rating ->
                assertEquals("Filter by hotel rating didn't work",
                        rating, TestData.TEST_RATING.getNumberOfStars()));
    }
}
