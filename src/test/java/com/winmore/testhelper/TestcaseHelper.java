package com.winmore.testhelper;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;
public class TestcaseHelper{
    
    public void submissionFileGenerationCheckInSubmissionGenerationModal() throws Exception {

        boolean isDisplayed = false;
        boolean failed = false;
    
        // Wait for the Modal to appear
        String submissionGenerationModal = PageInitializer.submissionpage.Submission_File_Generation_Modal;
        PageInitializer.actionHelper.waitForElementToBeVisible(submissionGenerationModal);
        isDisplayed = true;
    
        if (isDisplayed) {
    
            // ✅ isElementDisplayedShortTimeCheck returns boolean
            if (PageInitializer.validationHelper.isElementDisplayedShortTimeCheck(
                PageInitializer.submissionpage.Submission_File_Generated_Success_Message)) {
                failed = false;
                Log.info("✅ Submission file generated successfully");
    
            } else if (PageInitializer.validationHelper.isElementDisplayedShortTimeCheck(
                PageInitializer.submissionpage.Submission_File_Generated_Partial_Message)) {
                failed = true;
                Log.info("⚠️ Submission file partially generated");
    
            } else if (PageInitializer.validationHelper.isElementDisplayedShortTimeCheck(
                PageInitializer.submissionpage.Submission_File_Generated_Error_Message)) {
                failed = true;
                Log.info("❌ Submission file generation error");
    
            } else {
                failed = true;
                Log.info("❌ No result message appeared after modal opened");
            }
    
            // ✅ Always close modal after check
            PageInitializer.clickHelper.click(PageInitializer.submissionpage.Generation_Modal_Close_Button);
    
        } else {
            Log.info("<<< Submission generation modal is not opened >>>");
        }
    
        // ✅ Final result log
        if (failed) {
            Log.info("<<< Submission file generation failed / partially failed >>>");
        }
    }

    public void piTableExportGenerationCheck() throws Exception{
    boolean isDisplayed=false;
    boolean failed=false;

    String exportGenerationModal = PageInitializer.priceInspectorPage.PI_File_Generation_Modal;
    PageInitializer.actionHelper.waitForElementToBeVisible(exportGenerationModal);
    isDisplayed=true;

    if (isDisplayed) {
        PageInitializer.commonUtils.sleep(10000);

        if (PageInitializer.validationHelper.isElementDisplayedShortTimeCheck(PageInitializer.priceInspectorPage.PI_File_Generation_Completed_Message)) {
            failed=false;
            Log.info("✅ PI Table file generated successfully");
            PageInitializer.clickHelper.click(PageInitializer.priceInspectorPage.Progress_Modal_Close_Btn);
           // PageInitializer.clickHelper.click(PageInitializer.priceInspectorPage.DownloadExcelCloseBtn);
            
        }
        else if (PageInitializer.validationHelper.isElementDisplayedShortTimeCheck(PageInitializer.priceInspectorPage.PI_File_Generation_Failed_Message)) {
            failed=true;
            Log.info("❌ PI Table file generated Failed");

        }
        else {
            failed = true;
            Log.info("❌ No result message appeared after modal opened");
        }

    
    
    }

    else{
        Log.info("<<< PI generation modal is not opened >>>");

        }
 // ✅ Final result log
        if (failed) {
            Log.info("<<< PI file generation failed>>>");
        }
        

    
    }
}

