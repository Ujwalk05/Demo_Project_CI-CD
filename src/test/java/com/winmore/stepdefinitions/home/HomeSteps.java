package com.winmore.stepdefinitions.home;

import com.winmore.pageinitializer.PageInitializer;

import io.cucumber.java.en.Then;

public class HomeSteps extends PageInitializer{
	
	@Then("the user navigating to the {string} option in homepage")
	public void the_user_selecting_option_from_quick_create_list(String string) {
		if(string.equals("Profile Image")) {
			actionHelper.waitForElementToBeVisible(homePage.quickCreateBtn);
			clickHelper.click(homePage.quickCreateBtn);
			commonUtils.sleep(8000);
		}else {
			actionHelper.waitForElementToBeVisible(homePage.quickCreateBtn);
			clickHelper.click(homePage.quickCreateBtn);
			commonUtils.sleep(8000);
		}
	}

}
