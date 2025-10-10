package com.winmore.stepdefinitions.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.winmore.actions.InputActionHelper;
import com.winmore.base.TestBase;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;
import com.winmore.utils.TestDataConstants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CreateRecordSteps extends PageInitializer{
	
	public static Logger log = LogManager.getLogger(CreateRecordSteps.class);
	
	@Given("the user selecting the {string} tab")
	public void the_user_selecting_the_tab(String optionName) {
		actionHelper.waitForElementToBeVisible(homePage.browseButton);
		clickHelper.click(homePage.browseButton);
		commonUtils.sleep(4000);
		Log.info("✅ User clicked on browse successfully");
	}

	@Then("the user navigating to the {string} options")
	public void user_selecting_record_Template(String optionName) {
		switch (optionName) {
		case "Bids":
			actionHelper.waitForElementToBeVisible(homePage.bids);
			clickHelper.click(homePage.bids);
			Log.info("✅ User navigated to Bids option successfully");
			break;

		case "RateAIAutos":
			actionHelper.waitForElementToBeVisible(homePage.rateAIAutos);
			clickHelper.click(homePage.rateAIAutos);
			Log.info("✅ User navigated to RateAIAutos option successfully");
			break;

		default:
			break;
		}
	}

	@Then("the user navigating to the RateAILatest option")
	public void the_user_navigating_to_the_rate_ai_latest_option() {
		actionHelper.waitForElementToBeVisible(homePage.rateAILatest);
		clickHelper.click(homePage.rateAILatest);
		Log.info("✅ User navigated to RateAIAutos option successfully");
	}

	@Then("the user selecting the Create Record button")
	public void the_user_selecting_the_create_record_button() {
		commonUtils.sleep(2000);
		clickHelper.click(homePage.createRecord);
		Log.info("✅ User clicked on create record button successfully");
	}

	@Then("the user adding record name {string} in Create Record screen")
	public void the_user_adding_record_name_in_create_record_screen(String recordName) {
		commonUtils.sleep(2000);
		inputHelper.fill(homePage.addRecordName, recordName);
	}

	@Then("the user selecting the Create button")
	public void the_user_selecting_the_create_button() {
		actionHelper.waitForElementToBeVisible(homePage.createButton);
		clickHelper.click(homePage.createButton);
		commonUtils.sleep(2000);
	}

	@Then("the user verifying the presence of Record Creation Success screen")
	public void the_user_verifying_the_presence_of_record_creation_success_screen() {
		commonUtils.sleep(2000);
		validationHelper.isElementPresent(homePage.createRecordSuccess);
		Log.info("✅ User verified the presence of record creation success successfully");
	}
	
	@Given("the user moving to the record from record creation success screen")
	public void the_user_moving_to_the_record_from_record_creation_success_screen() {
	    clickHelper.click(homePage.createRecordSuccess);
	    commonUtils.sleep(2000);
	}
	
	@Then("the user verifying the presence of Record screen")
	public void the_user_verifying_the_presence_of_Record_screen() {
		commonUtils.sleep(2000);
		validationHelper.isElementPresent(homePage.settingsGearButton);
		Log.info("✅ User is on the created record screen");
	}
	
	@Given("the user selecting the Record screen Settings Gear button")
	public void the_user_selecting_the_record_screen_settings_gear_button() {
		clickHelper.click(homePage.settingsGearButton);
		commonUtils.sleep(2000);
		Log.info("✅ User clicked on the settings gear button");
	}

	@Given("the user selecting the Record Delete button")
	public void the_user_selecting_the_record_delete_button() {
		clickHelper.click(homePage.deleteRecord);
		commonUtils.sleep(2000);
	}

	@Then("the user selecting the Confirm Record Delete button")
	public void the_user_selecting_the_confirm_record_delete_button() {
		clickHelper.click(homePage.confirmDeleteRecord);
		commonUtils.sleep(2000);
		Log.info("✅ User deleted the created record successfully");
	}
	
	@Given("the user selecting the {string} template from record screen")
	public void the_user_selecting_the_template_from_record_screen(String templateName) {
		String oceanPricingElement = String.format(homePage.oceanPricingElement, templateName);
	    actionHelper.waitForElementToBeVisible(oceanPricingElement);
	    clickHelper.click(oceanPricingElement);
	}
	
	@Then("the user verifying the presence of {string} template screen")
	public void the_user_verifying_the_presence_of_template_screen(String oceanpricing) throws AutomationException {
		validationHelper.verifyElementContainsText(homePage.pageHeaderTitle, oceanpricing);
	    Log.info("✅ User verified the "+ oceanpricing +" header in record screen successfully");
	}
	
	@Then("the user verifying the {string} from Record overview screen")
	public void the_user_verifying_the_from_record_overview_screen(String overviewheader) throws AutomationException {
		validationHelper.verifyElementContainsText(homePage.pageHeaderTitle, overviewheader);
	    Log.info("✅ User verified the "+ overviewheader +" header in record screen successfully");
	}
	@Then ("the user adding details in Create Record screen for creating a record with name {string}")
	public void the_user_adding_details_in_create_record_screen_for_creating_a_record_with_name(String recordName) throws Exception {
		actionHelper.waitForElementToBeVisible(homePage.addRecordName);
		inputHelper.fill(homePage.addRecordName, recordName);
		Log.info("✅ User added record name as: " + recordName);

		inputHelper.selectDropdownOption(homePage.UserAccount, TestDataConstants.AccountName);
		Log.info("✅ User selected account as: " + TestDataConstants.AccountName);
		Thread.sleep(2000);

		clickHelper.click(homePage.BidDate);
		clickHelper.click(homePage.CalenderNextMonthBtn_InCreateRecordScreen);
		clickHelper.click(homePage.CalenderDate_4thRaw4col_InCreateRecordScreen);

		//inputHelper.selectOption(homePage.ScopeSelectionDropdownInputInCreationLayout, TestDataConstants.Scope_RFP);
		inputHelper.selectDropdownOption(homePage.ScopeSelectionDropdownInputInCreationLayout, TestDataConstants.Scope_RFP);
		Log.info("✅ User selected scope as: " + TestDataConstants.Scope_RFP);

		//inputHelper.selectOption(homePage.ModeSelectionDropdownInputInCreationLayout, TestDataConstants.Mode_Tl);
		inputHelper.selectDropdownOption(homePage.ModeSelectionDropdownInputInCreationLayout, TestDataConstants.Mode_Tl);
		Log.info("✅ User selected mode as: " + TestDataConstants.Mode_Tl);

		//inputHelper.selectOption(homePage.CommoditySelectionDropdownInputInCreationLayout, TestDataConstants.Commodity_General);
		inputHelper.selectDropdownOption(homePage.CommoditySelectionDropdownInputInCreationLayout, TestDataConstants.Commodity_General);
		Log.info("✅ User selected commodity as: " + TestDataConstants.Commodity_General);


	}

    @And ("the user selecting the Create Record screen Create button")
	public void the_user_selecting_the_create_record_screen_create_button() {
		clickHelper.click(homePage.RecordCreate);
		Log.info("✅ User clicked on Create button in slideout header");
	}
    
	

}
