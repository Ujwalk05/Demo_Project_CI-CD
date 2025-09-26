package com.winmore.stepdefinitions.home;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;
import com.winmore.utils.PlaywrightDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class OceanPricingSteps extends PageInitializer{
	
	public static Page page;

	public OceanPricingSteps() {

		page = PlaywrightDriver.getPage();

	}
	
	@Given("the user selecting the {string} button in the opean pricing page")
	public void the_user_selecting_the_button_in_the_opean_pricing_page(String shipperimport) {
		String shipperimportbutton = "//*/text()[normalize-space(.)='" + shipperimport + "']/parent::*";
	    actionHelper.waitForElementToBeVisible(shipperimportbutton);
	    clickHelper.click(shipperimportbutton);
		Log.info("✅ User clicked shipper import successfully");
	}
	
	@Then("the user uploading the shipper file {string} in Price Import screen")
	public void the_user_uploading_the_shipper_file_in_price_import_screen(String string) throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "/src/test/resources/ExcelData/KimberlyClark_FormulaCheckFile.xlsx");
        FileChooser fileChooser = page.waitForFileChooser(() -> {
            page.locator(".dropzone-empty").click();
        });
        fileChooser.setFiles(filePath);
        page.waitForLoadState();
        commonUtils.sleep(2000);
	}
	
	@Then("the user clicking the {string} button")
	public void the_user_clicking_the_button(String buttonName) {
		commonUtils.sleep(2000);
		String locator = "";
	    switch (buttonName.toLowerCase()) {
	        case "import":
	            locator = "//div[@class='client-components-table-renderer-components-modal-upload-pricing-component-__header-module__paddedContainer']//span[contains(text(),'Import')]";
	            break;
	        case "proceed":
	            locator = "//div[@class='client-components-table-renderer-components-modal-upload-pricing-component-__header-module__paddedContainer']//span[contains(text(),'Proceed')]";
	            break;
	        default:
	            throw new RuntimeException("No locator defined for button: " + buttonName);
	    }
	    actionHelper.waitForElementToBeVisible(locator);
	    clickHelper.click(locator);
		Log.info("✅ User clicked price import successfully");
		commonUtils.sleep(4000);
	}
	
	@Then("the user verifying that the Header row prediction is {string}")
	public void the_user_verifying_that_the_header_row_prediction_is(String expectedcount) throws AutomationException {
		boolean presence = false;
	    String rowCountShowing = validationHelper.getTrimmedText(pricingPage.rowNumber);
	    System.out.println("Row Count showing is " + rowCountShowing);
	    System.out.println("Row Count expected is " + expectedcount);

	    if (!rowCountShowing.equals(expectedcount)) {
	        System.out.println("Row Count does not match. Adding rows " + expectedcount + " to the input field.");
	        inputHelper.clear("//input[@placeholder='Enter row number here']");
	        inputHelper.fill("//input[@placeholder='Enter row number here']", expectedcount);
	    } else {
	        System.out.println("Row Count matches. No action needed.");
	    }
	    rowCountShowing = validationHelper.getTrimmedText("//tr[@class='client-components-table-renderer-components-modal-upload-pricing-component-sheet-selection-__sheet-selection-module__selected']//input[@placeholder='Enter row number here']/..//input");
	    presence = rowCountShowing.contains(expectedcount);
	    Assert.assertTrue(presence, 
	        "Header row count showing (" + rowCountShowing + ") is not matched with the expected (" + expectedcount + ")");
	}

}
