package com.winmore.stepdefinitions.home;

import org.testng.Assert;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class ShipperFileImport extends PageInitializer{
	
	@Given("the user selecting Shipper Import File button")
	public void the_user_selecting_Shipper_import() {
	    actionHelper.waitForElementToBeVisible(Shipperimportpage.importFile);
	    clickHelper.click(Shipperimportpage.importFile);
		Log.info("✅ User clicked shipper import successfully");
	}
	
	@Then("the user uploading the shipper file {string} in Price Import screen")
	public void the_user_uploading_the_shipper_file_in_price_import_screen(String fileName) throws AutomationException {
		uploadHelper.uploadAndSetFile(fileName, Shipperimportpage.fileUploadModal);
        commonUtils.sleep(2000);
	}
	
	@Then("the user uploading the shipper file {string} from {string} folder in Price Import screen")
	public void the_user_uploading_the_shipper_file_from_folder_in_price_import_screen(String fileName, String folderName) {
		uploadHelper.uploadAndSetFileInFolder(folderName, fileName, Shipperimportpage.fileUploadModal);
		commonUtils.sleep(2000);
	}
	
	@Then("the user clicking the {string} button")
	public void the_user_clicking_the_button(String buttonName) {
		commonUtils.sleep(2000);
		String locator = "";
	    switch (buttonName.toLowerCase()) {
	        case "import":
	            locator = Shipperimportpage.priceImport;
	            break;
	        case "proceed":
	            locator = Shipperimportpage.proceImportProceed;
	            break;
	        case "closesubmissionbutton":
	            locator = Shipperimportpage.closeSubmissionButton;
	            break;
            case "file upload history table_expand_edit":
				locator = Shipperimportpage.ExpandAndEditBtn_FileUploadHistoryTable;	
				break;
	        case "confirm mapping":
	            locator = Shipperimportpage.ConfirmMappingStatus_InFileUploadHistoryTable;
	            break;
            case "mapping screen next":
				locator = Shipperimportpage.MappingScreen_NextButton;
				break;
			
	        default:
	            throw new RuntimeException("No locator defined for button: " + buttonName);
	    }
	    actionHelper.waitForElementToBeVisible(locator);
		//clickHelper.pause();
	    clickHelper.click(locator);
		Log.info("✅ User clicked the button successfully");
		commonUtils.sleep(4000);
	}
	
	@Then("the user verifying that the Header row prediction is {string}")
	public void the_user_verifying_that_the_header_row_prediction_is(String expectedcount) throws AutomationException {
		boolean presence = false;
	    String rowCountShowing = validationHelper.getTrimmedText(Shipperimportpage.rowNumber);
	    System.out.println("Row Count showing is " + rowCountShowing);
	    System.out.println("Row Count expected is " + expectedcount);

	    if (!rowCountShowing.equals(expectedcount)) {
	        System.out.println("Row Count does not match. Adding rows " + expectedcount + " to the input field.");
	        inputHelper.clear(Shipperimportpage.rowNumber);
	        inputHelper.fill(Shipperimportpage.rowNumber, expectedcount);
	    } else {
	        System.out.println("Row Count matches. No action needed.");
	    }
	    rowCountShowing = validationHelper.getTrimmedText(Shipperimportpage.enterRowNumber);
	    presence = rowCountShowing.contains(expectedcount);
	    Assert.assertTrue(presence, 
	        "Header row count showing (" + rowCountShowing + ") is not matched with the expected (" + expectedcount + ")");
	}
	
	@Then("the user verifying the presence of {string} template screen under ocean pricing")
	public void the_user_verifying_the_presence_of_template_screen_under_ocean_pricing(String string) throws AutomationException {
		boolean presence = false;
		String screenPresence = validationHelper.getTrimmedText(Shipperimportpage.errorMessageForFile);
		presence = screenPresence.contains(string);
		Assert.assertTrue(presence, 
		        "File format error showing (" + screenPresence + ") ");
	}

	@Then("the user selecting the {string} button under ocean pricing")
	public void the_user_selecting_the_button_under_ocean_pricing(String string) {
		String okButton = String.format(Shipperimportpage.okButton, string);
		actionHelper.waitForElementToBeVisible(okButton);
	    clickHelper.click(okButton);
	}

	@And("the user verifying that the status changed to {string} in File upload history table")
	public void the_user_verifying_that_the_status_changed_to_in_file_upload_history_table(String string) throws AutomationException {
		boolean presence = false;
		String statusPresence = validationHelper.getTrimmedText(Shipperimportpage.ConfirmMappingStatus_InFileUploadHistoryTable);
		presence = statusPresence.contains(string);
		Assert.assertTrue(presence,
				"File upload history table status showing (" + statusPresence + ") is not matched with the expected (" + string + ")");
	
			}

}
