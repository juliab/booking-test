package com.seroshtan.bookingtest.pages;

import com.seroshtan.bookingtest.webelements.SearchForm;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;

import static com.seroshtan.bookingtest.pages.MainPage.DEFAULT_URL;

/**
 * This class represents main page of booking.com web-site.
 *
 * @author Julia Siroshtan
 */
@DefaultUrl(DEFAULT_URL)
public class MainPage extends PageObject {

    public final static String DEFAULT_URL = "http://www.booking.com/";

    @FindBy(id = SearchForm.ID)
    private SearchForm searchForm;

    /**
     * Perform hotel search from the main page.
     * After place entered to 'Destination/Hotel Name' input field,
     * first option provided in autocomplete is chosen.
     *
     * @param place Place to search hotels in.
     * @param checkIn Check-in date.
     * @param checkOut Check-out date.
     */
    public void searchFor(String place, LocalDate checkIn, LocalDate checkOut) {
        searchForm.searchFor(place, checkIn, checkOut);
    }

    /**
     * Perform hotel search from the main page.
     * After place entered to 'Destination/Hotel Name' input field,
     * first option provided in autocomplete is chosen.
     *
     * @param place Place to search hotels in.
     */
    public void searchFor(String place) {
        searchForm.searchFor(place, null, null);
    }
}
