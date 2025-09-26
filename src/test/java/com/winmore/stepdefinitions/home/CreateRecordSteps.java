package com.winmore.stepdefinitions.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CreateRecordSteps extends PageInitializer{
	
	public static Logger log = LogManager.getLogger(CreateRecordSteps.class);

	@Then("the user navigating to the RateAIAutos option")
	public void the_user_navigating_to_the_rate_ai_autos_option() {
		actionHelper.waitForElementToBeVisible("//a[normalize-space()='RateAIAutos']");
		clickHelper.click("//a[normalize-space()='RateAIAutos']");
		Log.info("✅ User navigated to RateAIAuto option successfully");
	}

	@Then("the user selecting the Create Record button")
	public void the_user_selecting_the_create_record_button() {
		commonUtils.sleep(2000);
		clickHelper.click("//span[@class='-u-btn__content']//span[contains(text(),'Create Record')]");
		commonUtils.sleep(2000);
		Log.info("✅ User clicked on create record button successfully");
	}

	@Then("the user adding record name {string} in Create Record screen")
	public void the_user_adding_record_name_in_create_record_screen(String newrateaiproject) {
		commonUtils.sleep(2000);
		inputHelper.fill("(//ul[@class='flexible-list list-unstyled layout-group']//input[@class='-u-form-group__input -u-form-group__input--text'])[1]", newrateaiproject);
	}

	@Then("the user selecting the Create button")
	public void the_user_selecting_the_create_button() {
		actionHelper.waitForElementToBeVisible("//div[@class='slideout-header -u-flex--none']//span[contains(text(),'Create')]");
		clickHelper.click("//div[@class='slideout-header -u-flex--none']//span[contains(text(),'Create')]");
		commonUtils.sleep(2000);
	}

	@Then("the user verifying the presence of Record Creation Success screen")
	public void the_user_verifying_the_presence_of_record_creation_success_screen() {
		commonUtils.sleep(2000);
		validationHelper.isElementPresent("(//div[@class='record-status-button-container']//button[@class='-u-btn -u-flex--none -u-btn--pill -u-btn--block'])[1]");
		Log.info("✅ User verified the presence of record creation success successfully");
	}
	
	@Given("the user moving to the record from record creation success screen")
	public void the_user_moving_to_the_record_from_record_creation_success_screen() {
	    clickHelper.click("(//div[@class='record-status-button-container']//button[@class='-u-btn -u-flex--none -u-btn--pill -u-btn--block'])[1]");
	    commonUtils.sleep(2000);
	}
	
	@Then("the user verifying the presence of Record screen")
	public void the_user_verifying_the_presence_of_Record_screen() {
		commonUtils.sleep(2000);
		validationHelper.isElementPresent("//i[@class='-u-icon -u-icon--gear -u-flex--none']");
		Log.info("✅ User is on the created record screen");
	}
	
	@Given("the user selecting the Record screen Settings Gear button")
	public void the_user_selecting_the_record_screen_settings_gear_button() {
		actionHelper.waitForElementToBeVisible("//i[@class='-u-icon -u-icon--gear -u-flex--none']");
		clickHelper.click("//i[@class='-u-icon -u-icon--gear -u-flex--none']");
		commonUtils.sleep(2000);
	}

	@Given("the user selecting the Record Delete button")
	public void the_user_selecting_the_record_delete_button() {
		clickHelper.click("//li[@class='list-group-item']//span[contains(text(), 'Delete' )]");
		commonUtils.sleep(2000);
	}

	@Then("the user selecting the Confirm Record Delete button")
	public void the_user_selecting_the_confirm_record_delete_button() {
		clickHelper.click("//div[@class='confirm-slideout u-confirm-slideout']//a[contains(text(),'Delete')]");
		commonUtils.sleep(2000);
		Log.info("✅ User deleted the created record successfully");
	}
	
	@Given("the user selecting the {string} template from record screen")
	public void the_user_selecting_the_template_from_record_screen(String oceanpricing) {
		String oceanpricingelement = "//a[@class='app-item selected'] /../a[.='" + oceanpricing + "']";
	    actionHelper.waitForElementToBeVisible(oceanpricingelement);
	    clickHelper.click(oceanpricingelement);
	}
	@Then("the user verifying the presence of {string} template screen")
	public void the_user_verifying_the_presence_of_template_screen(String oceanpricing) throws AutomationException {
		validationHelper.verifyElementContainsText("//h2[@class='pageheader__title']", oceanpricing);
	    Log.info("✅ User verified the "+ oceanpricing +" header in record screen successfully");
	}
	
	@Then("the user verifying the {string} from Record overview screen")
	public void the_user_verifying_the_from_record_overview_screen(String overviewheader) throws AutomationException {
		validationHelper.verifyElementContainsText("//h2[@class='pageheader__title']", overviewheader);
	    Log.info("✅ User verified the "+ overviewheader +" header in record screen successfully");
	}
}
