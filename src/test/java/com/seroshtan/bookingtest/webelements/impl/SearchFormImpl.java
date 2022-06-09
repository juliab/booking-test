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
    private final static String AUTO_COMPLETE_FIRST_XPATH = ".//li[@data-i='0']";
    private final static String DATES_XPATH = ".//*[@class='xp__dates xp__group']";

    private final static String DATE_FROM_XPATH = ".//*[@data-bui-ref='calendar-month'][1]";

    private final static String DATE_TO_XPATH = ".//*[@data-bui-ref='calendar-month'][2]";
    private final static String SEARCH_BUTTON_XPATH = ".//button[@data-sb-id='main']";

    @FindBy(id = PLACE_INPUT_ID)
    private WebElementFacade placeInput;

    @FindBy(xpath = AUTO_COMPLETE_FIRST_XPATH)
    private WebElementFacade autoCompleteFirst;

    @FindBy(xpath = DATES_XPATH)
    private WebElementFacade datesButton;

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
        callCalendar();
        selectFromDate(checkIn);
        selectToDate(checkOut);
        search();
    }

    private void enterPlace(String place) {
        placeInput.waitUntilEnabled();
        placeInput.clear();
        placeInput.sendKeys(place);
        autoCompleteFirst.waitUntilClickable();
        autoCompleteFirst.click();
        autoCompleteFirst.waitUntilNotVisible();
    }

    private void callCalendar() {
        if (!dateFrom.isVisible()) {
            datesButton.click();
        }
        dateFrom.waitUntilVisible();
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
