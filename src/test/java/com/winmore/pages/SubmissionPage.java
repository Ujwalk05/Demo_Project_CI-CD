package com.winmore.pages;

public class SubmissionPage {
    
public String SubmissionActionsBtn = "//span[@class='submission-actions']/i";
public String SubmissionActionsBtn_description = "The submission actions btn element.";

public String CreateSubmissionBtn = "//button//span[contains(text(),'Create Submission')] | //span[normalize-space()='Create Submission']";
public String CreateSubmissionBtn_description = "The create submission btn for 'Create Submission'.";

public String ProceedBtn_inSubmissionConfirmation = "//button//span[contains(text(),'Proceed')]";
public String ProceedBtn_inSubmissionConfirmation_description = "The proceed btn in submission confirmation for 'Proceed'.";


public String Submission_File_Generation_Modal= "//span[text()='Submission Files Generation']";
public String Submission_File_Generation_Modal_description = "The submission file generation modal for 'Submission Files Generation'";

public String Submission_File_Generated_Success_Message= "//div[text()='Submission files generated successfully']";
public String Submission_File_Generated_Success_Message_description = "The submission file generated success message for 'Submission files generated successfully'.";

public String Submission_File_Generated_Partial_Message="//div[contains(text(),'Submission files generation completed partially')]";
public String Submission_File_Generated_Partial_Message_description = "The submission file generated partial message for 'Submission files generation completed partially'.";

public String Submission_File_Generated_Error_Message="//div[contains(text(),'Submission files generation failed due to')]";
public String Submission_File_Generated_Error_Message_description = "The submission file generated error message for 'Submission files generation failed due to'.";

public String Generation_Modal_Close_Button= "(//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white'])[last()]";
public String Generation_Modal_Close_Button_description = "The generation modal close button element.";

}
