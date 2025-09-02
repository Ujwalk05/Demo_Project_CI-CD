package com.winmore.stepImplementations;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.PlaywrightDriver;
import com.winmore.utils.TestEnvironmentData;
import com.winmore.utils.Log;
import io.cucumber.datatable.DataTable;

public class LoginStepsImpl extends PageInitializer{
	
	public static Logger log = LogManager.getLogger(LoginStepsImpl.class);

	public static void naviagteToURL(String appName) throws AutomationException {
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

	public static void verifyLoginScreen() {
		commonUtils.sleep(2000);
		boolean SignInBtnPresence = validationHelper.isElementPresent(loginPage.sighInButton);
		Assert.assertEquals(true, SignInBtnPresence);
		Log.info("✅ Verified presence of login screen successfully.");
		
	}

	public static void loginToApp(DataTable tablevalue) {
		List<List<String>> data = tablevalue.asLists(String.class);
		String Email = data.get(1).get(0);
		String Password = data.get(1).get(1);
		inputHelper.type(loginPage.email, Email);
		inputHelper.type(loginPage.password, Password);
		clickHelper.click(loginPage.sighInButton);
		
	}

	public static void selectOrganization(String string) {
		//PlaywrightDriver.getPage().waitForURL("**/organization**"); // Waits for the URL containing "organization"
	    actionHelper.waitForElementToBeVisible(loginPage.selectOrgElement);
		if (validationHelper.isElementPresent(loginPage.selectOrgElement)) {
			clickHelper.click(loginPage.engineeringAutomation);
		} else {
			System.out.println("<<<<< Org selection screen isn't shown for this user >>>>>");
			 Log.error("<<<<< Org selection screen isn't shown for this user >>>>>");
		}
		switch (string) {
        case "Engineering Automation":
        	clickHelper.click("//a[contains(text(),'Aggregate Shipments')][1]");
            break;
            
        case "Close":
        	clickHelper.click(homePage.closeButton);
            break;
            
        case "Create Record":
        	clickHelper.click("//span[@class='-u-btn__content']//span[contains(text(),'Create Record')]");
            break;
            
        default:
            throw new IllegalArgumentException("❌ Unknown button: " + "Unknown");
		}
		
	}

	public static void verifySuccessfulLogin() {
		actionHelper.waitForElementToBeVisible(loginPage.userProfileImage);
		commonUtils.sleep(5000);
		boolean UserProfileImage = validationHelper.isElementPresent(loginPage.userProfileImage);
		Assert.assertEquals(true, UserProfileImage);
		Log.info("✅ Login is successful in the application");
	}

	public static void verifyTitle() {
		String title = validationHelper.getPageTitle();
		Assert.assertEquals("Winmore", title);
		Log.info("✅ Verified the title of the page after login");
	}

	public static void selectButton(String buttonName) throws InterruptedException {
		//loginPage.logoutButton.wait();

	    switch (buttonName) {
	        case "Logout":
	        	clickHelper.click(loginPage.logoutButton);
	            break;
	            
	        case "Close":
	        	clickHelper.click(homePage.closeButton);
	            break;
	            
	        case "Create Record":
	        	clickHelper.click("//span[@class='-u-btn__content']//span[contains(text(),'Create Record')]");
	            break;
	            
	        default:
	            throw new IllegalArgumentException("❌ Unknown button: " + buttonName);
	            
	    }
		//clickHelper.click(loginPage.logoutButton);
		Log.info("✅ User logged out from the application successfully");
		
	}


}
