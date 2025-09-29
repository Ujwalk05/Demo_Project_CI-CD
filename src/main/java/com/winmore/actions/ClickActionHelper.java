package com.winmore.actions;

import org.testng.Assert;
import com.microsoft.playwright.Page;
import com.winmore.utils.PlaywrightDriver;

/**
 * Helper class for performing various click actions using Playwright.
 */
public class ClickActionHelper {

    private final Page page;

    /**
     * Constructs a ClickActionHelper.
     */
    public ClickActionHelper() {
        this.page = PlaywrightDriver.getPage();
    }

    /**
     * Clicks on an element using a standard left-click.
     *
     * @param locatorKey The key or locator string for the element.
     */
    public void click(String locatorKey) {
        try {
            page.locator(locatorKey).click();
        } catch (Throwable t) {
            Assert.fail("Failed to perform a standard click on locator '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Hovers over an element and then performs a click.
     * This is useful for elements that become visible or clickable on hover.
     *
     * @param locatorKey The key or locator string for the element.
     */
    public void clickAndHover(String locatorKey) {
        try {
            page.locator(locatorKey).hover();
            page.locator(locatorKey).click();
        } catch (Throwable t) {
            Assert.fail("Failed to hover and click on locator '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Clicks an element after waiting for it to be visible and enabled.
     * This is a safer click for dynamic pages to prevent "Element not found" errors.
     *
     * @param locatorKey The key or locator string for the element.
     */
    public void clickWithWait(String locatorKey) {
        try {
            page.locator(locatorKey).waitFor();
            page.locator(locatorKey).click();
        } catch (Throwable t) {
            Assert.fail("Failed to wait for and click on locator '" + locatorKey + "': " + t.getMessage());
        }
    }
}