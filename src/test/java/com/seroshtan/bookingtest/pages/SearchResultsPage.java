package com.seroshtan.bookingtest.pages;

import com.seroshtan.bookingtest.beans.Filter;
import com.seroshtan.bookingtest.webelements.SearchForm;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.seroshtan.bookingtest.util.StringUtil.intFromString;

/**
 * This class represents search results page on booking.com web-site.
 *
 * @author Julia Siroshtan
 */
public class SearchResultsPage extends PageObject {

    private final static String HOTEL_LIST_ID = "hotellist_inner";
    private final static String OVERLAY_MSG_CSS = "div.overlay_msg";
    private final static By HOTEL_LOCATOR = By.xpath("./div[@data-hotelid]");
    private final static By HOTEL_NAME_LOCATOR = By.xpath(".//*[@class='sr-hotel__name']");
    private final static By HOTEL_LOCATION_LOCATOR = By.xpath(".//*[@class='address']/a");
    private final static By HOTEL_RATING_LOCATOR = By.xpath(".//*[contains(@class, 'stars ratings_stars')]");
    private final static String SORT_BY_STARS_CSS = "li.sort_stars";
    private final static By STARS_FROM_5_TO_1_LOCATOR = By.linkText("stars [5 to 1]");

    @FindBy(id = HOTEL_LIST_ID)
    private WebElementFacade hotelList;

    @FindBy(id = SearchForm.ID)
    private SearchForm searchForm;

    @FindBy(css = OVERLAY_MSG_CSS)
    private WebElementFacade overlayMsg;

    @FindBy(css = SORT_BY_STARS_CSS)
    private WebElementFacade sortByStars;

    /**
     * Read locations of found hotels into a list.
     *
     * @return List of hotel locations.
     */
    public List<String> readHotelLocations() {
        List<String> result = new ArrayList<>();
        List<WebElementFacade> hotels = hotelList.thenFindAll(HOTEL_LOCATOR);
        hotels.stream().forEach(hotel -> {
            String location = hotel.then(HOTEL_LOCATION_LOCATOR).getText();
            result.add(location);
        });
        return result;
    }

    /**
     * Read ratings of found hotels into a list.
     *
     * @return List of hotel ratings.
     */
    public List<Integer> readHotelRatings() {
        List<Integer> result = new ArrayList<>();
        List<WebElementFacade> hotels = hotelList.thenFindAll(HOTEL_LOCATOR);
        hotels.stream().forEach(hotel -> {
            String location = hotel.then(HOTEL_RATING_LOCATOR).getText();
            result.add(intFromString(location));
        });
        return result;
    }

    /**
     * Perform hotel search from the results page.
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
     * Apply filter by hotel rating.
     *
     * @param rating Hotel rating (number of stars).
     */
    public void filterHotelsBy(Filter.Rating rating) {
        find(rating.getLocator()).click();
        overlayMsg.waitUntilVisible();
        overlayMsg.waitUntilNotVisible();
    }

    /**
     * Set sorting by hotel rating.
     */
    public void sortHotelsByRating() {
        sortByStars.click();
        sortByStars.then(STARS_FROM_5_TO_1_LOCATOR).click();
        overlayMsg.waitUntilVisible();
        overlayMsg.waitUntilNotVisible();
    }
}
