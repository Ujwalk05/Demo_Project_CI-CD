package com.winmore.stepdefinitions.login;

import com.winmore.pageinitializer.PageInitializer;
import com.winmore.pages.HomePage;
import com.winmore.pages.LoginPage;
import com.winmore.stepImplementations.LoginStepsImpl;
import com.winmore.utils.Log;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSteps extends PageInitializer{
	
	@Given("^the user navigates to \"([^\"]*)\" application$")
	public void the_user_navigates_to(String appName) throws Throwable {
		LoginStepsImpl.naviagteToURL(appName);
	}
	
	@Then("^the user verifying the presence of login page$")
	public void verifyThePresenceOFLoginScreen() throws Throwable {
		LoginStepsImpl.verifyLoginScreen();
	}
	
	@Given("the user logging into the app")
	public void the_user_logging_into_the_app(DataTable tablevalue) {
		LoginStepsImpl.loginToApp(tablevalue);
	}

	@Given("the user selecting the Organization {string}")
	public void the_user_selecting_the_organization(String string) {
		LoginStepsImpl.selectOrganization(string);
	}

	@Then("the user verifying the successful login")
	public void the_user_verifying_the_successful_login() {
		LoginStepsImpl.verifySuccessfulLogin();
	}
	
	@Then("the user verifying the title of the homepage")
	public void the_user_verifying_the_title_of_the_homepage() {
		LoginStepsImpl.verifyTitle();
	}

	@Given("the user navigating to the {string} option")
	public void the_user_navigating_to_the_option(String string) {

		if(string.equals(string)) {
			clickHelper.click(loginPage.userProfileImage);
		}else {
			clickHelper.click(loginPage.userProfileImage);

		}
		//commonUtils.sleep(4000);
	}

	@Then("the user selecting the {string} button")
	public void the_user_selecting_the_button(String buttonName) throws InterruptedException {
		LoginStepsImpl.selectButton(buttonName);
		commonUtils.sleep(4000);
	}

}
