package com.winmore.stepdefinitions.login;

import java.util.List;

import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;
import com.winmore.utils.PlaywrightDriver;
import com.winmore.utils.TestEnvironmentData;
import org.apache.logging.log4j.Logger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSteps extends PageInitializer{
	
	public static Logger log = LogManager.getLogger(LoginSteps.class);
	
	@Given("^the user navigates to \"([^\"]*)\" application$")
	public void the_user_navigates_to(String appName) throws Throwable {
		if (appName.equalsIgnoreCase("Winmore")) {
	    	String env = TestEnvironmentData.getEnv();
	    	String baseURL = TestEnvironmentData.getTestDataBasedOnEnvironment("WinmoreBaseURl");
	        System.out.println("🌍 Launching " + appName + " app on environment [" + env + "] at: " + baseURL);
	        Log.info("🌍 Launching " + appName + " app on environment [" + env + "] at: " + baseURL);
	        PlaywrightDriver.openPage(baseURL);
	        actionHelper.waitForAngular();
	        actionHelper.waitForNetworkIdle();
	        actionHelper.waitForPageLoad();
	        System.out.println("Launching app URL");
	        Log.info("✅ Application URL launched successfully.");
	    } else {
	        System.out.println("❌ Please provide a valid app name, got: " + appName);
	        Log.error("❌ Invalid app name provided: " + appName);
	    }
	}
	
	@Then("^the user verifying the presence of login page$")
	public void verifyThePresenceOFLoginScreen() throws Throwable {
		commonUtils.sleep(2000);
		boolean SignInBtnPresence = validationHelper.isElementPresent(loginPage.sighInButton);
		Assert.assertEquals(true, SignInBtnPresence);
		Log.info("✅ Verified presence of login screen successfully.");
	}
	
	@Given("the user logging into the app")
	public void the_user_logging_into_the_app(DataTable tablevalue) {
		List<List<String>> data = tablevalue.asLists(String.class);
		String Email = data.get(1).get(0);
		String Password = data.get(1).get(1);
		inputHelper.type(loginPage.email, Email);
		inputHelper.type(loginPage.password, Password);
		clickHelper.click(loginPage.sighInButton);
	}

	@Given("the user selecting the Organization {string}")
	public void the_user_selecting_the_organization(String string) {
		actionHelper.waitForElementToBeVisible(loginPage.selectOrgElement);
		if (validationHelper.isElementPresent(loginPage.selectOrgElement)) {
			clickHelper.click(loginPage.engineeringAutomation);
		} else {
			System.out.println("<<<<< Org selection screen isn't shown for this user >>>>>");
			 Log.error("<<<<< Org selection screen isn't shown for this user >>>>>");
		}
	}

	@Then("the user verifying the successful login")
	public void the_user_verifying_the_successful_login() {
		commonUtils.sleep(5000);
		actionHelper.waitForElementToBeVisible(loginPage.userProfileImage);
		commonUtils.sleep(5000);
		boolean UserProfileImage = validationHelper.isElementPresent(loginPage.userProfileImage);
		Assert.assertEquals(true, UserProfileImage);
		Log.info("✅ Login is successful in the application");
	}
	
	@Then("the user verifying the title of the homepage")
	public void the_user_verifying_the_title_of_the_homepage() {
		String title = validationHelper.getPageTitle();
		Assert.assertEquals("Winmore", title);
		Log.info("✅ Verified the title of the page after login");
	}

	@Given("the user navigating to the {string} option")
	public void the_user_navigating_to_the_option(String string) {
		if(string.equals(string)) {
			clickHelper.click(loginPage.userProfileImage);
		}else {
			clickHelper.click(loginPage.userProfileImage);
		}
	}

	@Then("the user selecting the {string} button")
	public void the_user_selecting_the_button(String buttonName) throws InterruptedException {
		switch (buttonName) {
        case "Logout":
        	clickHelper.click(loginPage.logoutButton);
            break;
            
        case "Close":
        	clickHelper.click(homePage.closeButton);
            break;
            
        case "Quick Create Save":
        	clickHelper.click("//span[contains(text(),'Save')]");
            break;
            
        case "Create Record":
        	clickHelper.click("//span[@class='-u-btn__content']//span[contains(text(),'Create Record')]");
            break;
            
        default:
            throw new IllegalArgumentException("❌ Unknown button: " + buttonName);
            
    }
	//clickHelper.click(loginPage.logoutButton);
	Log.info("✅ User logged out from the application successfully");
		commonUtils.sleep(4000);
	}

}
