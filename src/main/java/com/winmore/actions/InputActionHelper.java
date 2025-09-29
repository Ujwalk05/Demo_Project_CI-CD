package com.winmore.actions;

import java.nio.file.Path;

import org.testng.Assert;
import com.microsoft.playwright.Page;
import com.winmore.utils.PlaywrightDriver;

/**
 * A helper class for performing various input actions on web elements
 * using Playwright.
 */
public class InputActionHelper {

    private final Page page;

    /**
     * Constructs an InputActionHelper instance.
     * Initializes the Page object from PlaywrightDriver.
     */
    public InputActionHelper() {
        this.page = PlaywrightDriver.getPage();
    }

    /**
     * Enters a value into a text input field.
     * This is the recommended method for filling forms. It waits for the element
     * to be visible and editable before clearing and filling the input.
     *
     * @param locatorKey The locator for the input element (e.g., "#username", ".email-field").
     * @param value      The string value to be entered.
     */
    public void fill(String locatorKey, String value) {
        try {
            page.locator(locatorKey).fill(value);
        } catch (Throwable t) {
            Assert.fail("Failed to fill input '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Types a value into a text input field with a simulated keyboard press.
     * This method is slower than {@code fill()} as it simulates typing character by character.
     * It's useful for testing scenarios where an input has a type-ahead feature or a listener
     * that triggers on each keystroke.
     *
     * @param locatorKey The locator for the input element.
     * @param value      The string value to be typed.
     */
    public void type(String locatorKey, String value) {
        try {
            page.locator(locatorKey).type(value);
        } catch (Throwable t) {
            Assert.fail("Failed to type into input '" + locatorKey + "': " + t.getMessage());
        }
    }
    
    /**
     * Clears the content of a text input field.
     * This is a good practice before entering new text to ensure the field is empty.
     *
     * @param locatorKey The locator for the input element.
     */
    public void clear(String locatorKey) {
        try {
            page.locator(locatorKey).clear();
        } catch (Throwable t) {
            Assert.fail("Failed to clear input '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Selects one or more options in a <select> element.
     * The method accepts a variable number of string values to select.
     *
     * @param locatorKey The locator for the select element.
     * @param values     The values of the options to be selected.
     */
    public void selectOption(String locatorKey, String... values) {
        try {
            page.locator(locatorKey).selectOption(values);
        } catch (Throwable t) {
            Assert.fail("Failed to select options for '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Checks a checkbox or a radio button.
     * This method ensures the element is checked without toggling its state if it's already checked.
     *
     * @param locatorKey The locator for the checkbox or radio button.
     */
    public void check(String locatorKey) {
        try {
            page.locator(locatorKey).check();
        } catch (Throwable t) {
            Assert.fail("Failed to check element '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Unchecks a checkbox or a radio button.
     * This method ensures the element is unchecked without toggling its state if it's already unchecked.
     *
     * @param locatorKey The locator for the checkbox or radio button.
     */
    public void uncheck(String locatorKey) {
        try {
            page.locator(locatorKey).uncheck();
        } catch (Throwable t) {
            Assert.fail("Failed to uncheck element '" + locatorKey + "': " + t.getMessage());
        }
    }

    /**
     * Uploads one or more files to an input type="file" element.
     * The method accepts a variable number of file paths.
     *
     * @param locatorKey The locator for the file input element.
     * @param filePaths  The paths to the files to upload.
     */
    public void setInputFiles(String locatorKey, Path... filePaths) {
        try {
            page.locator(locatorKey).setInputFiles(filePaths);
        } catch (Throwable t) {
            Assert.fail("Failed to set input files for '" + locatorKey + "': " + t.getMessage());
        }
    }
}