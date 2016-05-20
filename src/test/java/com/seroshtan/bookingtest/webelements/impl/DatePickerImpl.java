package com.seroshtan.bookingtest.webelements.impl;

import com.seroshtan.bookingtest.webelements.DatePicker;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.time.LocalDate;

/**
 * This is a datepicker element implementation.
 *
 * @author Julia Siroshtan
 */
public class DatePickerImpl extends WebElementFacadeImpl implements DatePicker {

    public DatePickerImpl(WebDriver driver, ElementLocator locator, WebElement webElement,
                          long implicitTimeoutInMilliseconds, long waitForTimeoutInMilliseconds) {
        super(driver, locator, webElement, implicitTimeoutInMilliseconds, waitForTimeoutInMilliseconds);
    }

    @Override
    public void selectDate(LocalDate date) {
        waitUntilClickable();
        click();
        then(buildSelectDateXpath(date)).click();
    }

    private String getMonthYear(LocalDate date) {
        String month = date.getMonth().toString();
        // Capitalize first letter of month
        month = month.substring(0, 1) + month.substring(1, month.length()).toLowerCase();
        return month + " " + date.getYear();
    }

    private By buildSelectDateXpath(LocalDate date) {
        return By.xpath("./following-sibling::*//th[text()='" + getMonthYear(date)
                + "']/ancestor::table//td[*[contains(text(), '"
                + date.getDayOfMonth() + "')]]");
    }
}
