package com.winmore.actions;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import com.winmore.utils.PlaywrightDriver;
import com.microsoft.playwright.Page.WaitForSelectorOptions;

public class ValidationActionHelper {
	
	public static Page page;

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

}
