package com.seroshtan.bookingtest.enums;

import org.openqa.selenium.By;

/**
 * This class represents values that can be used when filtering hotels on the search results page.
 *
 * @author Julia Siroshtan
 */
public class Filter {

    public enum Rating {
        UNRATED(0),
        ONE_STAR(1),
        TWO_STARS(2),
        THREE_STARS(3),
        FOUR_STARS(4),
        FIVE_STARS(5);

        Rating(int numberOfStars) {
            this.numberOfStars = numberOfStars;
        }

        private final int numberOfStars;

        public Integer getNumberOfStars() {
            return numberOfStars;
        }

        public By getLocator() {
            return By.xpath(".//*[@data-filters-item='class:class=" + ordinal() + "']");
        }
    }
}
