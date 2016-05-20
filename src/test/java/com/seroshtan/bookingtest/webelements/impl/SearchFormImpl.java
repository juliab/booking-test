package com.seroshtan.bookingtest.webelements.impl;

import com.seroshtan.bookingtest.webelements.DatePicker;
import com.seroshtan.bookingtest.webelements.SearchForm;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.time.LocalDate;

public class SearchFormImpl extends WidgetObjectImpl implements SearchForm {

    private final static String PLACE_INPUT_ID = "ss";
    private final static String AUTO_COMPLETE_FIRST_CSS = ".c-autocomplete__list li:first-child";
    private final static String DATE_FROM_XPATH = ".//*[@data-mode='check-in']";
    private final static String DATE_TO_XPATH = ".//*[@data-mode='check-out']";
    private final static String SEARCH_BUTTON_XPATH = ".//button[@type='submit'][*[text() = 'Search']]";

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

    public SearchFormImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    @Override
    public void searchFor(String place, LocalDate checkIn, LocalDate checkOut) {
        enterPlace(place);
        if (checkIn != null) {
            selectFromDate(checkIn);
        }
        if (checkOut != null) {
            selectToDate(checkOut);
        }
        search();
    }

    private void enterPlace(String place) {
        placeInput.clear();
        placeInput.sendKeys(place);
        autoCompleteFirst.waitUntilVisible();
        autoCompleteFirst.click();
        autoCompleteFirst.waitUntilNotVisible();
    }

    private void selectFromDate(LocalDate date) {
        dateFrom.selectDate(date);
    }

    private void selectToDate(LocalDate date) {
        dateTo.selectDate(date);
    }

    private void search() {
        searchButton.click();
    }
}
