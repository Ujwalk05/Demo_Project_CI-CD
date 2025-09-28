package com.winmore.stepdefinitions.quickcreate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;
import io.cucumber.java.en.Then;

public class QuickCreateSteps extends PageInitializer{
	
	public static Logger log = LogManager.getLogger(QuickCreateSteps.class);
	
	@Then("the user selecting {string} option from Quick Create list")
	public void the_user_selecting_option_from_quick_create_list(String option) {
		String activity = "//span[normalize-space()='" + option + "']";
	    actionHelper.waitForElementToBeVisible(activity);
	    clickHelper.click(activity);
	    Log.info("✅ User clicked on the schedule activity option after clicking on the quick create button");
	}
	
	@Then("the user verifying that the {string} activity screen in quick create is opened")
	public void the_user_verifying_that_the_activity_screen_in_quick_create_is_opened(String schedule) throws AutomationException {
	    validationHelper.verifyElementContainsText(quickCreatePage.activityHeader, schedule);
	    Log.info("✅ User verified the schedule activity screen successfully");
	}

	@Then("the user adding details in Activity Quick Create screen")
	public void the_user_adding_details_in_activity_quick_create_screen() {
		commonUtils.sleep(2000);
		inputHelper.fill(quickCreatePage.activityName, "Quick Create Auto Note");
	}

	@Then("the user verifying the Activity creation completion")
	public void the_user_verifying_the_activity_creation_completion() throws AutomationException {
		if(validationHelper.isElementDisplayedShortTimeCheck(quickCreatePage.saveQuickCreate))
		{
			System.out.println("Checking element is present in the screen, expectation failed");
			Assert.assertEquals(false, true);
		} else
		{
			System.out.println("Checking element is not present in the screen, as expected");
			Assert.assertEquals(false, false);
		}
	}
	

}
