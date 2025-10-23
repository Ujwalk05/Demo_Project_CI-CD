package com.winmore.stepdefinitions.PriceInspector;

import org.testng.Assert;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testingdata.PropertyDataHandler;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class PITable extends PageInitializer {

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_PI_table_Expand_button(String buttonName) throws AutomationException {
        String locator;

        switch (buttonName.toLowerCase()) {
            case "pi table _expand":
                locator = priceInspectorPage.PI_Table_Expand_Btn;
                break;
            default:
                throw new RuntimeException("No locator defined for button: " + buttonName);
        }

        actionHelper.waitForElementToBeVisible(locator);
        clickHelper.click(locator);
        Log.info("✅ Clicked PI table '" + buttonName + "' button successfully");
    }

@And ("the user verifying the imported data row count from PI table for {string}")
public void the_user_verifying_the_imported_data_row_count_from_PI_table_for(String ShipperFileName) throws AutomationException {
   String expectedRowCount="";
    // Wait (poll) for PI table to be available, up to ~25s total
        int attempts = 0;
        while (attempts < 5) {
            boolean tableVisible = validationHelper.isElementDisplayedShortTimeCheck(
                priceInspectorPage.PITableImportedDataRowCounts_PaginationPITable
            );
            if (tableVisible) {
                Log.info("<<<< The PI table is getting loaded >>>>");
                break;
            } else {
                attempts++;
                commonUtils.sleep(5000);
                Log.info("PI table loading taking more time");
            }
        }
    switch (ShipperFileName) {
        case "Lowes_Mini_Example":
         expectedRowCount = PropertyDataHandler.fetchPropertyValue("TestData", "Lowes_Mini_Example_DataRowCounts");
            break;
    
        default:
         throw new AutomationException("Please Provide Valid Shipper File Name");

    }

    String actualRowCount = validationHelper.getTrimmedText(priceInspectorPage.PITableImportedDataRowCounts_PaginationPITable);

    if (actualRowCount.contains(expectedRowCount)) {
            Assert.assertTrue(true);
        } else {
        throw new AutomationException("Expected row count: " + expectedRowCount + ", but found: " + actualRowCount);
        }
    Log.info("✅ Verified imported data row count from PI table for '" + ShipperFileName + "' successfully");
    }

@Given("the user selecting the 3dot option for the column {string} in PI Table")
public void the_user_selecting_the_3dot_option_for_the_column_in_PI_Table(String columnName) throws AutomationException {
    
    actionHelper.waitForElementToBeVisible(String.format(priceInspectorPage.PI_Column_Header_Custom_Xpath_New, columnName));
    String threeDotOptionLocator = String.format(priceInspectorPage.PI_Column_Header_Custom_Xpath_New, columnName);
    System.out.println(threeDotOptionLocator);
    clickHelper.click(threeDotOptionLocator);
    Log.info("✅ User clicked on 3dot option for the column '" + columnName + "' in PI Table successfully");


}

@Then("the user clicks on the button {string}")
public void the_user_clicks_on_the_button(String buttonName) throws AutomationException {
    String locator;

    switch (buttonName.toLowerCase()) {
        case "add to shipper update_3dot option":
            locator = priceInspectorPage.PITable_Column_3DotOption_AddToShipperUpdate;
            break;
        default:
            throw new RuntimeException("No locator defined for button: " + buttonName);
    }

    actionHelper.waitForElementToBeVisible(locator);
    clickHelper.click(locator);
    Log.info("✅ Clicked on the button '" + buttonName + "' successfully");
}

@Given("the user verifying that the PI table is showing the column {string}")
public void the_user_verifying_that_the_PI_table_is_showing_the_column(String columnName) throws AutomationException {
    String locator="";
    switch (columnName.toLowerCase()) {
        case "origin hours":
            locator = priceInspectorPage.PITable_ColumnHeader_OriginHours;
            break;  
        case "adhoc dest hour":
            locator = priceInspectorPage.PITable_ColumnHeader_AdhocDestHour;
            break;
        default:
            throw new RuntimeException("No locator defined for column: " + columnName);
    }   
    actionHelper.waitForElementToBeVisible(locator);
    boolean isDisplayed = validationHelper.isElementDisplayedShortTimeCheck(locator);
    Assert.assertTrue(isDisplayed, "PI table is not showing the column: " + columnName);
    Log.info("✅ Verified that the PI table is showing the column '" + columnName + "' successfully");
}

}