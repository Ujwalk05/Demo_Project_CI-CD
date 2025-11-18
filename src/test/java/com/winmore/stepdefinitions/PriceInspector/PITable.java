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

        switch (buttonName) {
            case "PI table _Expand":
                locator = priceInspectorPage.PI_Table_Expand_Btn;
                break;

            case "Pricing Modal Close":    
                locator = priceInspectorPage.PriceEditModal_CloseBtn;
                break;

                case "PI table _Close":
                locator = priceInspectorPage.PITable_CloseBtn;
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
    actionHelper.hoverOverElement(threeDotOptionLocator);
    actionHelper.waitForElementToBeVisible(priceInspectorPage.PITable_Column_3DotOption_Button);
    clickHelper.click(priceInspectorPage.PITable_Column_3DotOption_Button);
    Log.info("✅ User clicked on 3dot option for the column '" + columnName + "' in PI Table successfully");


}

@Then("the user clicks on the button {string}")
public void the_user_clicks_on_the_button(String buttonName) throws AutomationException {
    String locator;

    switch (buttonName.toLowerCase()) {
        case "add to shipper update_3dot option":
            locator = priceInspectorPage.PITable_Column_3DotOption_AddToShipperUpdate;
            break;

        case "pi table 1st row expand":
            locator = priceInspectorPage.PITable_1stRow_Expand_btn;
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

@Then("the user verifying that the Price Edit modal showing the {string}")
public void the_user_verifying_that_the_Price_Edit_modal_showing(String tabName) throws AutomationException {
   String locator="";
   switch(tabName.toLowerCase()){
       case "ad hoc tab":
           locator = priceInspectorPage.PriceEditModal_AdHoc_Tab;
           break;

         case "origin hours column":
           locator = priceInspectorPage.PriceEditModal_OriginHoursLabel;
           break;

           case "adhoc dest hour column":
           locator = priceInspectorPage.PriceEditModal_AdHocDestHourLabel;
           break;

       default:
           throw new RuntimeException("No locator defined for tab: " + tabName);
   }
   
   
   
   // locator = String.format(priceInspectorPage.PriceEditModal_AdHoc_Tab, tabName);
    actionHelper.waitForElementToBeVisible(locator);
    boolean isDisplayed = validationHelper.isElementDisplayedShortTimeCheck(locator);
    Assert.assertTrue(isDisplayed, "Price Edit modal is not showing the tab: " + tabName);
    Log.info("✅ Verified that the Price Edit modal is showing the tab '" + tabName + "' successfully");
}

@And("the user clicking on the Tab {string} in Price Edit Modal")
public void the_user_clicking_on_the_Tab_in_Price_Edit_Modal(String tabName) throws AutomationException {
    String locator;

    switch (tabName.toLowerCase()) {
        case "ad hoc tab":
            locator = priceInspectorPage.PriceEditModal_AdHoc_Tab;
            break;


        default:
            throw new RuntimeException("No locator defined for tab: " + tabName);
    }

    actionHelper.waitForElementToBeVisible(locator);
    clickHelper.click(locator);
    Log.info("✅ Clicked on the tab '" + tabName + "' in Price Edit Modal successfully");
}


@Given("the user updating the {string} value from Price Edit modal screen")
public void the_user_updating_the_value_from_Price_Edit_modal_screen(String fieldName) throws AutomationException {
    String locator="";
    String valueToEnter="";
    switch (fieldName) {
        case "Origin Hours":
            locator = priceInspectorPage.PriceEditModal_OriginHours_ValueField;
            valueToEnter = PropertyDataHandler.fetchPropertyValue("TestData", "OriginHours_Value");
            actionHelper.waitForElementToBeVisible(locator);
            actionHelper.clearText(locator);
            actionHelper.TypeTextandPressEnter(locator, valueToEnter);
            clickHelper.click(priceInspectorPage.PriceEditModal_Updated_label);
            Log.info("✅ Updated the '" + fieldName + "' value from Price Edit modal screen successfully");

            break;  
        default:
            throw new RuntimeException("No locator defined for field: " + fieldName);
    }   
}


}