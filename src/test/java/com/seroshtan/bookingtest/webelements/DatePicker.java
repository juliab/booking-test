package com.seroshtan.bookingtest.webelements;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.LocalDate;

/**
 * This interface represents datepicker element from booking.com web-site.
 *
 * @author Julia Siroshtan
 */
@ImplementedBy(DatePickerImpl.class)
public interface DatePicker extends WebElementFacade {
    /**
     * Select required date from datepicker.
     *
     * @param date Date to be selected.
     */
    void selectDate(LocalDate date);
}
