package com.winmore.stepdefinitions.MappingProcess;

import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testhelper.MLCasesHelp;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class AutoMapped extends PageInitializer{

    @Then("the user verifying the presence of Adjust Auto-Mapped screen")
    public void the_user_verifying_the_presence_of_adjust_auto_mapped_screen() {
        actionHelper.waitForElementToBeVisible(autoMappingPage.AdjustAutoMappedScreen_Header);
        Log.info("✅ User is on the Adjust Auto-Mapped screen");
    }

   @And ("the user verifying all the mapped Excel columns header in Confirm Mapping screen for {string}")
   public void the_user_verifying_all_the_mapped_excel_columns_header_in_confirm_mapping_screen_for(String fileName) throws Throwable {
      switch (fileName) {
            case "Lowes_Mini_Example.xlsx":
              //  mlCasesHelp.columnMappingCheckWithExcel(initialMappingPage.ReviewScreen_MappedExcelColumnHeaderValue1, fileName, "lanes", 5, );
               
                for (int i = 1; i <= 18; i++) {
                String dynamicLocator = autoMappingPage.AdjustAutoMappedColumnHeaderValue(i);
                MLCasesHelp.columnMappingCheckWithExcel(dynamicLocator, fileName, "lanes", 5, i-1);
             //   MLCasesHelp.columnMappingCheckWithExcel(null, dynamicLocator, fileName, "lanes", 5, i-1);
                break;       
        }
		Log.info("✅ User successfully verified all the mapped Excel columns in Initial Review screen for " + fileName);
	
  }
}
}