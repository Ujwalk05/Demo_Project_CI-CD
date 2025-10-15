package com.winmore.stepdefinitions.MappingProcess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.stepdefinitions.quickcreate.QuickCreateSteps;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class InitialMapping extends PageInitializer{

	public static Logger log = LogManager.getLogger(QuickCreateSteps.class);

    @Given("the user verifying the presence of Initial Review screen")
	public void the_user_verifying_the_presence_of_screen() throws Exception {
	     actionHelper.waitForElementToBeVisible(initialMappingPage.InitialReviewScreen_Header);
         validationHelper.verifyElementContainsText(initialMappingPage.InitialReviewScreen_Header, "Initial Review");
            Log.info("✅ User successfully verified the presence of Initial Review screen");              
	}

	@Then("the user verifying all the mapped Excel columns in Initial Review screen for {string}")
	public void the_user_verifying_all_the_mapped_Excel_columns_in_Initial_Review_screen_for(String fileName) throws Throwable {
		switch (fileName) {
            case "Lowes_Mini_Example.xlsx":
              //  mlCasesHelp.columnMappingCheckWithExcel(initialMappingPage.ReviewScreen_MappedExcelColumnHeaderValue1, fileName, "lanes", 5, );
               
                for (int i = 1; i <= 15; i++) {
                String dynamicLocator = initialMappingPage.getReviewScreenMappedExcelColumnHeaderValue(i);
                System.out.println(dynamicLocator);
                mlCasesHelp.columnMappingCheckWithExcel(dynamicLocator, fileName, "lanes", 5, i-1);
                break;
        
            
     
            }
		Log.info("✅ User successfully verified all the mapped Excel columns in Initial Review screen for " + fileName);
	}
    }
}
