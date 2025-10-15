package com.winmore.stepdefinitions.MappingProcess;

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
        String RadioButton = adjustUnMappedPage.getImportAsAdhocColumnRadio(RadioButtonNumber);
        actionHelper.waitForElementToBeVisible(RadioButton);
        clickHelper.click(RadioButton);
        Log.info("✅ User clicked on the button import as AdHoc column_CheckBox " + RadioButtonNumber);
   
    }
}
