package com.winmore.stepdefinitions.MappingProcess;

import org.testng.Assert;

import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class AdjustUnmapped extends PageInitializer {

    @Then("the user verifying the presence of Adjust Unmapped screen")
    public void the_user_verifying_the_presence_of_adjust_unmapped_screen() {
        actionHelper.waitForElementToBeVisible(adjustUnMappedPage.AdjustUnmappedScreen_Header);
        Log.info("✅ User is on the Adjust Unmapped screen"); 
    }

    @Given("the user clicking on the button import as AdHoc column_CheckBox {string}")
    public void the_user_clicking_on_the_button_import_as_adhoc_column_checkBox(String RadioButtonNumber) {
        commonUtils.sleep(2000);
		String locator = "";
        switch (RadioButtonNumber) {
            case "1":
                locator = adjustUnMappedPage.ImportAsAdHocColumn_1stTable_MapUnmatched_Checkbox;
	            break;
            case "2":
                locator = adjustUnMappedPage.ImportAsAdHocColumn_2ndTable_MapUnmatched_Checkbox;
                break;
        
            default:
                break;
        }
        actionHelper.waitForElementToBeVisible(locator);
	    clickHelper.click(locator);
        Log.info("✅ User clicked on the import as AdHoc column_CheckBox " + RadioButtonNumber + " successfully");
   
    }

    @Then("the user updating the adhoc column {string} segment selection dropdown value as {string}")
    public void the_user_updating_the_adhoc_column_segment_selection_dropdown_value_as(String columnNumber, String segmentValue) throws com.winmore.exceptions.AutomationException {
        // Build dynamic locators
        String dropdownLocator = String.format(adjustUnMappedPage.Select_Segment_Dropdown_In_Mapping_Screen, columnNumber);
        String optionLocator = String.format(adjustUnMappedPage.Select_Specific_Segment_From_Dropdown, segmentValue);

        // Optional reference element to stabilize scroll if needed
        String ignoreRadioLocator = String.format(adjustUnMappedPage.Select_LeaveColumnUnmappedIgnoreColumn_MappingScreen, columnNumber);

        // Ensure dropdown is visible and open it
        actionHelper.waitForElementToBeVisible(dropdownLocator);
        clickHelper.click(dropdownLocator);

        boolean updated = false;
        String currentValue = "";

        // Try a few times in case the option panel re-renders
        for (int attempt = 1; attempt <= 3 && !updated; attempt++) {
            // If option not readily visible, re-open dropdown and try again
            if (!validationHelper.isElementDisplayedShortTimeCheck(optionLocator)) {
                // Nudge the viewport around related control to help rendering
                actionHelper.waitForElementToBeVisible(ignoreRadioLocator);
                commonUtils.sleep(300);
                clickHelper.click(dropdownLocator);
                commonUtils.sleep(300);
            }

            // Click desired option
            clickHelper.click(optionLocator);
            commonUtils.sleep(400);

            // Read current value shown on the dropdown/button
            currentValue = validationHelper.getTrimmedText(dropdownLocator);
            if (currentValue != null && currentValue.trim().toLowerCase().contains(segmentValue.trim().toLowerCase())) {
                updated = true;
                break;
            }

            // Re-open and retry
            clickHelper.click(dropdownLocator);
            commonUtils.sleep(250);
        }

        org.testng.Assert.assertTrue(updated,
                "Segment value not updated as expected. Expected: '" + segmentValue + "' but showing: '" + currentValue + "'");
        Log.info("✅ Updated adhoc column " + columnNumber + " segment to '" + segmentValue + "'");
    }
   @And("the user updating the adhoc column {string} adhoc type as {string}")
   public void the_user_updating_the_adhoc_column_adhoc_type_as(String columnNumber, String adhocType) throws com.winmore.exceptions.AutomationException {
        switch (adhocType.toLowerCase()) {
            case "other":
                String radioLocator = String.format(adjustUnMappedPage.AdhocType_Other_Generic_Xpath_In_Mapping_Screen, columnNumber);

                // Bring into view and click
                actionHelper.waitForElementToBeVisible(radioLocator);
                clickHelper.click(radioLocator);
                commonUtils.sleep(200);

                // Verify: radio is checked and its value matches expected type (with a tiny retry)
                boolean isChecked = false;
                String value = null;
                for (int i = 0; i < 3; i++) {
                    isChecked = com.winmore.utils.PlaywrightDriver.getPage().locator(radioLocator).isChecked();
                    value = com.winmore.utils.PlaywrightDriver.getPage().locator(radioLocator).getAttribute("value");
                    if (isChecked && value != null) break;
                    commonUtils.sleep(150);
                }

                Assert.assertTrue(isChecked,
                        "Adhoc type radio not selected for column " + columnNumber + " (locator: " + radioLocator + ")");
               Assert.assertTrue(value != null && value.equalsIgnoreCase(adhocType.trim()),
                        "Adhoc type value mismatch. Expected: '" + adhocType + "' but found: '" + value + "'");

                Log.info("✅ Updated adhoc column " + columnNumber + " adhoc type to '" + adhocType + "'");
                break;

            default:
                throw new RuntimeException("Unsupported adhoc type: " + adhocType + ". Add locator and handler if needed.");
        }
    }

    @And("the user updating the {string} column name to {string} in Map Unmatched Screen")
    public void the_user_updating_the_column_name_to_in_map_unmatched_screen(String TablePosition, String columnName) throws Throwable {
        String locator = "";
        switch (TablePosition) {
            case "second table":
                locator = adjustUnMappedPage.MapColumn_2ndTable_MapUnmatched;
                break;
        
            default:
                break;
        }
        actionHelper.waitForElementToBeVisible(locator);
        clickHelper.click(locator);
        actionHelper.clearText(locator);
        commonUtils.sleep(500);
        actionHelper.TypeTextandPressEnter(locator, columnName);
        commonUtils.sleep(1000);
        Log.info("✅ User updated the " + TablePosition + " column name to " + columnName + " in Map Unmatched Screen successfully");
    }
}
