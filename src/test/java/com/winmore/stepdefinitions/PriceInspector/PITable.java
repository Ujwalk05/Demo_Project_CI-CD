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

}
