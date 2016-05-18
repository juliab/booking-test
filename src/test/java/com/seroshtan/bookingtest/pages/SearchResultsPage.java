package com.seroshtan.bookingtest.pages;

import com.seroshtan.bookingtest.beans.Hotel;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.seroshtan.bookingtest.pages.SearchResultsPage.DEFAULT_URL;

/**
 * This class represents search results page on booking.com web-site.
 *
 * @author Julia Siroshtan
 */
@DefaultUrl(DEFAULT_URL)
public class SearchResultsPage extends PageObject {

    public final static String DEFAULT_URL = "http://www.booking.com/searchresults.html";
    private final static String HOTEL_LIST_ID = "hotellist_inner";
    private final static By HOTEL_LOCATOR = By.xpath("./div[@data-hotelid]");
    private final static By HOTEL_NAME_LOCATOR = By.xpath(".//*[@class='sr-hotel__name']");
    private final static By HOTEL_LOCATION_LOCATOR = By.xpath(".//*[@class='address']/a");

    @FindBy(id = HOTEL_LIST_ID)
    private WebElementFacade hotelList;

    /**
     * Map results found by booking.com search to list of hotel entities.
     *
     * @return List of hotels.
     */
    public List<Hotel> readHotels() {
        List<Hotel> result = new ArrayList<>();
        List<WebElementFacade> hotels = hotelList.thenFindAll(HOTEL_LOCATOR);
        for (WebElementFacade hotel : hotels) {
            String name = hotel.then(HOTEL_NAME_LOCATOR).getText();
            String location = hotel.then(HOTEL_LOCATION_LOCATOR).getText();
            result.add(new Hotel(name, location));
        }
        return result;
    }
}
