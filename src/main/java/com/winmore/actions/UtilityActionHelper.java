package com.winmore.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.winmore.utils.PlaywrightDriver;

public class UtilityActionHelper {

    private static Page page;

    public UtilityActionHelper() {
        page = PlaywrightDriver.getPage();
    }

    /**
     * Waits until Angular has finished processing all requests.
     */
    public void waitForAngular() {
        page.waitForFunction("() => { " +
            "const el = document.querySelector('html');" +
            "if (!window.angular) return true;" +
            "if (window.angular.getTestability) {" +
            "   return new Promise(resolve => {" +
            "       window.angular.getTestability(el).whenStable(() => resolve(true));" +
            "   });" +
            "} else {" +
            "   if (!angular.element(el).injector()) return true;" +
            "   return new Promise(resolve => {" +
            "       angular.element(el).injector().get('$browser')" +
            "          .notifyWhenNoOutstandingRequests(() => resolve(true));" +
            "   });" +
            "}" +
        "}");
    }

    /**
     * Waits until the page is fully loaded (document.readyState === complete).
     */
    public void waitForPageLoad() {
        page.waitForLoadState(LoadState.LOAD);
    }

    /**
     * Waits until network requests are idle (no active XHR/fetch for a while).
     */
    public void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    public void waitForElementToBeVisible(String locator) {
        try {
            // Wait for the element defined by the locator to be visible
            page.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
            System.out.println("✅ Element is visible: " + locator);
        } catch (TimeoutError e) {
            // Handle the case where the element doesn't become visible within the default timeout
            System.err.println("❌ Timeout waiting for element to be visible: " + locator);
            throw e; // Re-throw the exception to fail the test
        }
    }
    
    public String getRandomStringUsingDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		Date date = new Date();
		String filePathdate = dateFormat.format(date).toString();
		return filePathdate;
	}
public void clearText(String locator) {
    try {
        page.locator(locator).click();
        page.locator(locator).press("Control+A");
        page.locator(locator).press("Backspace");
        System.out.println("✅ Cleared text in element: " + locator);
    } catch (Exception e) {
        System.err.println("❌ Error clearing text in element: " + locator + " - " + e.getMessage());
        throw e; // Re-throw the exception to fail the test
    }
}
public void typeText(String locator, String text) {
    try {
        page.fill(locator, text); // Fill the input field with the specified text
        System.out.println("✅ Typed text '" + text + "' in element: " + locator);
    } catch (Exception e) {
        System.err.println("❌ Error typing text in element: " + locator + " - " + e.getMessage());
        throw e; // Re-throw the exception to fail the test
    }
}
    public void TypeTextandPressEnter(String locator, String text) {
        try {
            page.locator(locator).fill(text);
            page.locator(locator).press("Enter");
            System.out.println("✅ Typed text '" + text + "' and pressed Enter in element: " + locator);
        } catch (Exception e) {
            System.err.println("❌ Error typing text and pressing Enter in element: " + locator + " - " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }

    public void hoverOverElement(String locator) {
        try {
            page.hover(locator); // Hover over the specified element
            System.out.println("✅ Hovered over element: " + locator);
        } catch (Exception e) {
            System.err.println("❌ Error hovering over element: " + locator + " - " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }
}
