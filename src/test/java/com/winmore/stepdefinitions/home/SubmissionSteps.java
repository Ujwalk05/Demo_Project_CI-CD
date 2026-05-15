package com.winmore.stepdefinitions.home;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class SubmissionSteps extends PageInitializer {
    
    @And("the user clicking on the button {string}")
    public void Button_click(String buttonname) throws Exception{
        String locator="";

        switch (buttonname) {
            case "Submission Action":
            locator= submissionpage.SubmissionActionsBtn;      
             break;

            case "Create Submission":
            locator= submissionpage.CreateSubmissionBtn;
            break;    

            case "Proceed in Submission Confirmation":
            locator= submissionpage.ProceedBtn_inSubmissionConfirmation;
            break;
        
            default:
                throw new AutomationException("No locator defined for button: " + buttonname);
        }

        actionHelper.waitForElementToBeVisible(locator);
        clickHelper.click(locator);
        Log.info("✅ Clicked PI table '" + buttonname + "' button successfully");

        if(buttonname.equalsIgnoreCase("Proceed in Submission Confirmation")){
            testcasehelper.submissionFileGenerationCheckInSubmissionGenerationModal();
        }
        else{
            throw new RuntimeException("Proceed button is not enabled");
        }

    }
}
