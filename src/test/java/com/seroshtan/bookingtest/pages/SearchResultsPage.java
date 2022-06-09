package com.seroshtan.bookingtest.pages;

import com.seroshtan.bookingtest.enums.Filter;
import com.seroshtan.bookingtest.webelements.SearchForm;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents search results page on booking.com web-site.
 *
 * @author Julia Siroshtan
 */
public class SearchResultsPage extends PageObject {

    private final static String HOTEL_LIST_XPATH = "//div[@data-component='arp-properties-list']";
    private final static String OVERLAY_MSG_XPATH = ".//div[@data-testid='overlay-card']";
    private final static By HOTEL_LOCATOR = By.xpath(".//div[@data-testid='property-card']");
    private final static By HOTEL_NAME_LOCATOR = By.xpath(".//*[@class='sr-hotel__name']");
    private final static By HOTEL_LOCATION_LOCATOR = By.xpath(".//*[@data-testid='location']");
    private final static By HOTEL_RATING_LOCATOR = By.xpath(".//div[@data-testid='rating-stars']/* | .//div[@data-testid='rating-squares']/*");
    private final static String SORT_BAR_DROPDOWN_XPATH = "//ul[@role='menubar']/li[@data-id='dropdown']/a";
    private final static By SORT_DROPDOWN_LIST_XPATH = By.xpath("./following-sibling::ul");
    private final static By SORT_BY_STARS_HIGHEST_FIRST_LOCATOR = By.xpath("./li[@data-id='class']");

    @FindBy(xpath = HOTEL_LIST_XPATH)
    private WebElementFacade hotelList;

    @FindBy(id = SearchForm.ID)
    private SearchForm searchForm;

    @FindBy(xpath = OVERLAY_MSG_XPATH)
    private WebElementFacade overlayMsg;

    @FindBy(xpath = SORT_BAR_DROPDOWN_XPATH)
    private WebElementFacade sortBarDropdown;

    /**
     * Read locations of found hotels into a list.
     *
     * @return List of hotel locations.
     */
    public List<String> readHotelLocations() {
        List<String> result = new ArrayList<>();
        List<WebElementFacade> hotels = hotelList.thenFindAll(HOTEL_LOCATOR);
        hotels.forEach(hotel -> {
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
        hotels.forEach(hotel -> {
            List<WebElementFacade> stars = hotel.thenFindAll(HOTEL_RATING_LOCATOR);
            result.add(stars.size());
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
        sortBarDropdown.click();
        WebElementFacade sortDropdown = sortBarDropdown.then(SORT_DROPDOWN_LIST_XPATH);
        sortDropdown.waitUntilVisible();
        sortDropdown.then(SORT_BY_STARS_HIGHEST_FIRST_LOCATOR).click();
        overlayMsg.waitUntilVisible();
        overlayMsg.waitUntilNotVisible();
    }
}
