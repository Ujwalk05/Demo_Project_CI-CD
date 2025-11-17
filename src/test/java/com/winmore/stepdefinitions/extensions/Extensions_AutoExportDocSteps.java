package com.winmore.stepdefinitions.extensions;

import com.winmore.pageinitializer.PageInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Extensions_AutoExportDocSteps extends PageInitializer{
	
	@Then("the user navigating to the {string} option in browse")
	public void the_user_navigating_to_the_option_in_browse(String option) {
		String shipperimportbutton = String.format(homePage.aggregateShipment, option);
		clickHelper.click(shipperimportbutton);
		commonUtils.sleep(40000);
		
	}

	@Then("the user adding record name {string} in Create Record Screen")
	public void the_user_adding_record_name_in_create_record_screen(String string) {
	    
	}

	@Then("the user verifying the presence of {string} screen")
	public void the_user_verifying_the_presence_of_screen(String string) {
	    
	}

	@Given("the user moving to the record from record creation success screen1")
	public void the_user_moving_to_the_record_from_record_creation_success_screen1() {
	    
	}

	@Given("the user verifying that the checkbox {string} is {string}")
	public void the_user_verifying_that_the_checkbox_is(String string, String string2) {
	    
	}

	@Given("the user clicking on the button {string}")
	public void the_user_clicking_on_the_button(String string) {
	    
	}

	@Given("the user updating value in the field {string} as {string}")
	public void the_user_updating_value_in_the_field_as(String string, String string2) {
	    
	}

	@Given("the user verifying that the file is {string} in record screen")
	public void the_user_verifying_that_the_file_is_in_record_screen(String string) {
	    
	}

	@Given("the user verifying that the conversation related to {string} is not found in conversation screen")
	public void the_user_verifying_that_the_conversation_related_to_is_not_found_in_conversation_screen(String string) {
	    
	}

	@Given("the user updating {string} in record edit screen")
	public void the_user_updating_in_record_edit_screen(String string) {
	    
	}

	@Then("the user clicking on the link {string}")
	public void the_user_clicking_on_the_link(String string) {
	    
	}

	@Given("the user checking that the {string} file downloaded successfully")
	public void the_user_checking_that_the_file_downloaded_successfully(String string) {
	    
	}

	@Given("the user verifying the {string} from Conversation screen")
	public void the_user_verifying_the_from_conversation_screen(String string) {
	    
	}

	@Given("the user deleting {string} file from directory")
	public void the_user_deleting_file_from_directory(String string) {
	    
	}

	@Then("the user searching for a record {string}")
	public void the_user_searching_for_a_record(String string) {
	    
	}

	@Then("the user verifying the Record search result for {string}")
	public void the_user_verifying_the_record_search_result_for(String string) {
	    
	}

}
