package com.winmore.actions;

import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.winmore.exceptions.AutomationException;
import com.winmore.utils.PlaywrightDriver;
import com.microsoft.playwright.Page.WaitForSelectorOptions;

public class ValidationActionHelper {
	
	public static Page page;
	private static final Logger logger = LogManager.getLogger(ValidationActionHelper.class);

	public ValidationActionHelper() {

		page = PlaywrightDriver.getPage();

	}
	
	public boolean isElementPresent(String locatorKey) {

//		try {
//			page.waitForSelector(PlaywrightDriver.OR.getProperty(locatorKey),
//					new WaitForSelectorOptions().setTimeout(2000));
//
//			return true;
//		} catch (Throwable t) {
//
//			return false;
//		}
		
		try {
			page.waitForSelector(locatorKey, new WaitForSelectorOptions().setTimeout(2000));
			return true;
		} catch (Throwable t) {
			// Log at debug to avoid noisy logs; elevate to warn if needed
			logger.debug("Element not present or wait timed out for locator: {} - {}", locatorKey, t.getMessage());
			return false;
		}

	}
	
	public String title;

	public String getPageTitle() {
		try {
			title = page.title();
		} catch (Throwable t) {

			Assert.fail(t.getMessage());
		}
		return title;
	}
	
	public boolean verifyElementContainsText(String locatorKey, String expectedText) throws AutomationException {
	    boolean textContains = false;
	    try {

	        String actualText = page.locator(locatorKey).innerText().trim();

	        if (actualText.toLowerCase().contains(expectedText.trim().toLowerCase())) {
	            textContains = true;
	            System.out.println("Actual text is >>> " + actualText + " And the expected is >>> " + expectedText);
	        } else {
	            throw new AutomationException("Actual text is >>> " + actualText + " And the expected is >>> " + expectedText);
	        }

	    } catch (Exception e) {
	        throw new AutomationException("String Comparison failed " + e.getMessage());
	    }
	    return textContains;
	}
	
	public boolean isElementDisplayedShortTimeCheck(String elementExpression) throws AutomationException {
	    boolean elementDisplayed = false;
	    try {
	        if (elementExpression != null) {

	            // Short timeout check (e.g., 10s)
	            elementDisplayed = page.locator(elementExpression).isVisible(new Locator.IsVisibleOptions().setTimeout(10000));

	        } else {
	            throw new AutomationException(elementExpression + " Not found");
	        }
	    } catch (Exception e) {
	        elementDisplayed = false; // Fallback if element not found/visible
	    }
	    return elementDisplayed;
	}
	
	
	public String getTrimmedText(String locatorKey) throws AutomationException {
		try {
	        Locator element = page.locator(locatorKey);
	        String tagName = element.evaluate("el => el.tagName").toString().toLowerCase();
	        String text;

	        if ("input".equals(tagName) || "textarea".equals(tagName)) {
	            text = element.inputValue().trim();  // for input/textarea, get value
	        } else {
	            text = element.innerText().trim();   // for other elements, get innerText
	        }

	        System.out.println("Text fetched from locator (" + locatorKey + ") is: " + text);
	        return text;
	    } catch (Exception e) {
	        throw new AutomationException("Failed to fetch text for locator: " + locatorKey + " | Error: " + e.getMessage());
	    }
	}



}
