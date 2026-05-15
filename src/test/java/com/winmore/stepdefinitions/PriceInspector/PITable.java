package com.winmore.stepdefinitions.PriceInspector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.testng.Assert;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testingdata.PropertyDataHandler;
import com.winmore.utils.Log;
import com.winmore.utils.PlaywrightDriver;
import com.winmore.testhelper.MLCasesHelp;

import io.cucumber.java.en.*;

public class PITable extends PageInitializer {

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_PI_table_Expand_button(String buttonName) throws Exception {
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

            case "Update in Batch Update View":
                locator = priceInspectorPage.BatchUpdateView_UpdateBtn;
                break;

            case "PI Table Export Excel":
                locator = priceInspectorPage.PITable_Export_Excel_Btn;
                break;

            case "Download Modal Close":
                locator = priceInspectorPage.DownloadExcelCloseBtn;
                break;


                case "PI Table Export Excel Download":
                    triggerAndSaveDownload(priceInspectorPage.PITable_Export_Excel_Download_Btn);
                    Log.info("✅ Triggered and saved download for 'PI Table Export Excel Download'");
                    testcasehelper.piTableExportGenerationCheck();
                    return; // skip the generic click at the bottom

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
    String locator = null;
    Page page = PlaywrightDriver.getPage();

    switch (buttonName) {
        case "Add to Shipper Update_3Dot Option":
            locator = priceInspectorPage.PITable_Column_3DotOption_AddToShipperUpdate;
            break;

        case "PI Table 1st Row Expand":
            locator = priceInspectorPage.PITable_1stRow_Expand_btn;
            break;  
            
        case "Pricing Modal Close":
            locator = priceInspectorPage.PriceEditModal_CloseBtn;
            break;

        case "PI table _Close":
           locator = priceInspectorPage.PITable_CloseBtn;
            break;

        case "PITable_SelectAll_Checkbox":
            locator=priceInspectorPage.PITable_selectAllCheckbox;
            break;   
            
        case "Batch Update in PI Table":
            locator = priceInspectorPage.PITable_BatchUpdateBtn;

            // ✅ Special handling only for Batch Update
            if (page.locator(priceInspectorPage.PITableSavingInProgressIndicator)
                    .isVisible(new Locator.IsVisibleOptions().setTimeout(3000))) {
                page.locator(priceInspectorPage.PITableSavingInProgressIndicator)
                    .waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(60000));
            }
            actionHelper.waitForElementToBeVisible(priceInspectorPage.PITableSavingIndicator);
            break;

        case "Done in Batch update success pop up":
            int retryCount = 0;
            while (retryCount < 15) {
                if (validationHelper.isElementDisplayedShortTimeCheck(priceInspectorPage.BatchUpdate_SuccessPopup_DoneBtn)) {
                    locator = priceInspectorPage.BatchUpdate_SuccessPopup_DoneBtn;
                    break;
                }
                if (validationHelper.isElementDisplayedShortTimeCheck(priceInspectorPage.BatchUpdate_Modal_CloseBtn)) {
                    locator = priceInspectorPage.BatchUpdate_Modal_CloseBtn;
                    break;
                }
                retryCount++;
                commonUtils.sleep(1000);
            }

            if (locator == null) {
                throw new AutomationException(
                        "Neither 'Done' nor 'Close' button appeared in the batch update success popup within timeout.");
            }
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

        case "origin hours edited value from pricing modal":
            locator = priceInspectorPage.PITable_OriginHoursFirstRow_EditedValueFromPriceEditModal;
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
            commonUtils.sleep(2000);
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

            String updatedValue = PlaywrightDriver.getPage().locator(locator).first().inputValue();
            Assert.assertEquals(
                updatedValue.trim(),
                valueToEnter.trim(),
                "Origin Hours value was not updated in Price Edit modal."
            );

            actionHelper.waitForElementToBeVisible(priceInspectorPage.PriceEditModal_Updated_label);
            Log.info("✅ Updated the '" + fieldName + "' value from Price Edit modal screen successfully");

            break;  
        default:
            throw new RuntimeException("No locator defined for field: " + fieldName);
    }   
}

@And ("the user verifying that the {string} view is opened")
public void modal_verification(String View_name) throws AutomationException{
    String Locator;

    switch(View_name){

        case "Batch Update":
        Locator = priceInspectorPage.BatchUpdateView_Header;
        break;

        default:
        throw new RuntimeException("No locator defined for view: " + View_name);
    }

    actionHelper.waitForElementToBeVisible(Locator);
    boolean isDisplayed = validationHelper.isElementDisplayedShortTimeCheck(Locator);
    Assert.assertTrue(isDisplayed, View_name + " view is not opened");
     Log.info("✅ Verified that the '" + View_name + "' view is opened successfully");
}


@And("the user selecting the {string} field and giving New Value {string} in Batch Update View")
public void user_choosingFields_enteringValue_inBatchUpdate(String ColumnName, String Newvalue) throws Exception{
    String Locator;
    String pricingStatusOptionLocator = null;

    switch(ColumnName.toLowerCase()){

        case "adhoc dest hour":
            Locator = priceInspectorPage.BatchUpdateFieldOption_adhocDestHour;
            break;

        case "pricing status":
            Locator = priceInspectorPage.BatchUpdateFieldOption_PricingStatus;
            switch (Newvalue.trim()) {
			
                case "To Price":
                    pricingStatusOptionLocator = priceInspectorPage.BatchUpdate_PricingStatus_OptionsListed_ToPrice;
                    break;
                    
                case "Ready Custom":
                case "Ready":
                    pricingStatusOptionLocator = priceInspectorPage.BatchUpdate_PricingStatus_OptionsListed_Ready;
                    break;
    
                case "Awarded - Primary":
                    pricingStatusOptionLocator = priceInspectorPage.BatchUpdate_PricingStatus_OptionsListed_AwardedPrimary;
                    break;
              
                default:
                    throw new RuntimeException("Please provide a valid Pricing Status value in Batch Update screen: " + Newvalue);
                }
            break;
         
        default:
        throw new RuntimeException("No locator defined for column: " + ColumnName);
    }
    
    clickHelper.click(priceInspectorPage.Batchupdate_selectField);
    actionHelper.waitForElementToBeVisible(Locator);
    clickHelper.click(Locator);

    if (ColumnName.equalsIgnoreCase("pricing status")) {
        actionHelper.waitForElementToBeVisible(priceInspectorPage.BatchUpdate_NewValue_SelectionField);
        clickHelper.click(priceInspectorPage.BatchUpdate_NewValue_SelectionField);
        commonUtils.sleep(2000);
        actionHelper.waitForElementToBeVisible(pricingStatusOptionLocator);
        clickHelper.click(pricingStatusOptionLocator);
        clickHelper.click(priceInspectorPage.CancelBtn_slideout);

     } else {
        inputHelper.fill(priceInspectorPage.BatchUpdate_NewValueInput_Field, Newvalue);
    }

    Log.info("✅ User selected the '" + ColumnName + "' field and entered new value '" + Newvalue + "' in Batch Update View successfully");

}

@Then("the user handling the Batch Update Success popup")
public void the_user_handling_the_Batch_Update_Success_popup() throws AutomationException {
                // Playwright-based implementation for Batch Update Success popup handling
                Page page = PlaywrightDriver.getPage();
                String inProgressXpath = priceInspectorPage.BatchUpdateRecordWereUpdatedMessage;
                // Wait for the in-progress message to disappear (timeout: 180s)
                page.waitForSelector(inProgressXpath, new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(180_000));

                boolean handled = false;
                // Try "Records were updated" message
                String recordUpdatedMessage = priceInspectorPage.BatchUpdateRecordWereUpdatedMessage;
                try {
                    page.waitForSelector(recordUpdatedMessage, new Page.WaitForSelectorOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(10_000));
                    clickHelper.click(priceInspectorPage.BatchUpdate_SuccessPopup_DoneBtn);
                    handled = true;
                } catch (Exception e) {
                    // Move to next condition
                }

                if (!handled) {
                    // Try "Batch Update Completed Successfully" message
                    String batchUpdateCompletedMessageXpath = priceInspectorPage.BatchUpdateRecordWereUpdatedMessage;
                    try {
                        page.waitForSelector(batchUpdateCompletedMessageXpath, new Page.WaitForSelectorOptions()
                                .setState(WaitForSelectorState.VISIBLE)
                                .setTimeout(10_000));
                        System.out.println("<<< Batch Update Completed Successfully, closing the modal >>>");
                        clickHelper.click(priceInspectorPage.BatchUpdate_Modal_CloseBtn);
                        handled = true;
                    } catch (Exception e) {
                        // No success message found
                    }
                }

                if (!handled) {
                    throw new AutomationException("Neither 'Records were updated' nor 'Batch Update Completed Successfully' message appeared after batch update.");
                }
                Log.info("✅ Handled Batch Update Success popup successfully");
                
            }



            @Then("the user verifying that updated value is {string} for {string}")
            public void the_user_verifying_that_updated_value_is_for_for(String updatedValue, String columnName) throws AutomationException {
                String locator="";
                switch (columnName) {
                    case "adhoc dest hour column":
                        locator = priceInspectorPage.PITable_adhocDestHour_FirstRow_BatchUpdatedValue;
                        break;

                    case "Pricing Status column":
                        locator = priceInspectorPage.PITable_PricingStatusUpdatedvalue;
                        break;    

                        default:
                            throw new RuntimeException("No locator defined for column: " + columnName);
                }
                actionHelper.waitForElementToBeVisible(locator);
                String actualValue = validationHelper.getTrimmedText(locator);
                Assert.assertEquals(actualValue, updatedValue, "Updated value is not as expected for column: " + columnName);
                Log.info("✅ Verified that the updated value is '" + updatedValue + "' for column: " + columnName);
            }

            @Then("the user setting export file name as {string}")
            public void File_Name_Set(String filename) throws AutomationException{
                
                if(validationHelper.isElementPresent(priceInspectorPage.FileNameInputFieldInPIDownload)){
                    actionHelper.clearText(priceInspectorPage.FileNameInputFieldInPIDownload);
                    inputHelper.fill(priceInspectorPage.FileNameInputFieldInPIDownload, filename);
                    Log.info("File name is been successfully updated as "+ filename);
                }
                else{
                  throw new AutomationException("File name input field not found in PI download.");                }
            }


            @Then("the user verifying that the {string} file downloaded successfully")
            public void File_Download_Verification(String Filename) throws AutomationException {
                String expectedFilePrefix;
                String expectedExtension;
            
                switch (Filename) {
                    case "tl_pricing_bid_set.xlsx/tl_pricing":
                        expectedFilePrefix = "tl_pricing";
                        expectedExtension  = ".xlsx";
                        break;
            
                    default:
                        throw new RuntimeException(
                            "No download verification mapping defined for file: " + Filename);
                }
            
                MLCasesHelp.assertFileDownloaded(expectedFilePrefix, expectedExtension, Filename);
            }

            private void triggerAndSaveDownload(String downloadButtonLocator) throws AutomationException {
    try {
        // ✅ Only creates the folder if it doesn't already exist — no exception if folder is present
        if (!Files.exists(MLCasesHelp.FRAMEWORK_DOWNLOAD_DIR)) {
            Files.createDirectories(MLCasesHelp.FRAMEWORK_DOWNLOAD_DIR);
        }

        Page page = PlaywrightDriver.getPage();

        // ✅ Wait for button to be visible BEFORE registering download listener
        actionHelper.waitForElementToBeVisible(downloadButtonLocator);

        // ✅ Click happens inside lambda so Playwright can intercept the download event
        Download download = page.waitForDownload(() -> {
            clickHelper.click(downloadButtonLocator);
        });

        String suggestedFileName = download.suggestedFilename();
        Path targetPath = MLCasesHelp.FRAMEWORK_DOWNLOAD_DIR.resolve(suggestedFileName);
        download.saveAs(targetPath);
        Log.info("✅ Download saved to: " + targetPath);

    } catch (Exception e) {
        throw new AutomationException(
            "Failed to download and save file in DownloadedFiles folder.", e);
    }
}

}