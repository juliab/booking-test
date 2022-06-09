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

import static org.junit.Assert.assertTrue;

/**
 * Test class for hotels sorting on the results page.
 *
 * @author Julia Siroshtan
 */
@RunWith(SerenityRunner.class)
public class SortTest {

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
    public void sortByRating() {
        resultsPage.sortHotelsByRating();
        List<Integer> hotelRatings = resultsPage.readHotelRatings();
        for (int i = 0; i < hotelRatings.size() - 1; i++) {
            assertTrue("Hotels sort by rating didn't work",
                    hotelRatings.get(i) >= hotelRatings.get(i + 1));
        }
    }
}
