package com.seroshtan.bookingtest.pages;

import com.seroshtan.bookingtest.webelements.DatePicker;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
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
    private final static String PLACE_INPUT_ID = "ss";
    private final static String AUTO_COMPLETE_FIRST_CSS = ".c-autocomplete__list li:first-child";
    private final static String DATE_FROM_XPATH = "//*[@data-mode='check-in']";
    private final static String DATE_TO_XPATH = "//*[@data-mode='check-out']";
    private final static String SEARCH_BUTTON_XPATH = "//button[@type='submit'][*[text() = 'Search']]";

    @FindBy(id = PLACE_INPUT_ID)
    private WebElementFacade placeInput;

    @FindBy(css = AUTO_COMPLETE_FIRST_CSS)
    private WebElementFacade autoCompleteFirst;

    @FindBy(xpath = DATE_FROM_XPATH)
    private DatePicker dateFrom;

    @FindBy(xpath = DATE_TO_XPATH)
    private DatePicker dateTo;

    @FindBy(xpath = SEARCH_BUTTON_XPATH)
    private WebElementFacade searchButton;

    /**
     * Perform hotel search from the main page.
     *
     * @param place Place to search hotels in.
     * @param checkIn Check-in date.
     * @param checkOut Check-out date.
     */
    public void searchFor(String place, LocalDate checkIn, LocalDate checkOut) {
        placeInput.sendKeys(place);
        autoCompleteFirst.waitUntilVisible();
        autoCompleteFirst.click();
        autoCompleteFirst.waitUntilNotVisible();
        dateFrom.selectDate(checkIn);
        dateTo.selectDate(checkOut);
        searchButton.click();
    }
}
