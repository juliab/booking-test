package com.seroshtan.bookingtest.webelements;

import com.seroshtan.bookingtest.webelements.impl.SearchFormImpl;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WidgetObject;

import java.time.LocalDate;

/**
 * This class represents search from that can be found on main page and search results page.
 *
 * @author Julia Siroshtan
 */
@ImplementedBy(SearchFormImpl.class)
public interface SearchForm extends WidgetObject {

    /**
     * Search form locator.
     */
    String ID = "frm";

    /**
     * Perform hotels search.
     *
     * @param place Place to search hotels in.
     * @param checkIn Check-in date.
     * @param checkOut Check-out date.
     */
    void searchFor(String place, LocalDate checkIn, LocalDate checkOut);
}
