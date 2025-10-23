@RateAI @RegressionSet31 @RateAIAdhocColumnFromTask
Feature: RateAI_From Task_Adhoc Column testing

Scenario: Launching the app 
	Given the user navigates to "Winmore" application 
	Then the user verifying the presence of login page 

    Scenario: Login into the app
	Given the user logging into the app
 		| Email 						| password	|
		| automationtester@winmore.app		 	| Test@123		|
	And the user selecting the Organization "RateAI CurrencyAndFormula Automation"  
	Then the user verifying the successful login

    Scenario: User creating a record
	Given the user selecting the "Browse" tab 
	Then the user navigating to the "Bids" options 
	And the user selecting the Create Record button 
	Then the user adding details in Create Record screen for creating a record with name "Rate AI Task Shipper Adhoc Column testing" 
	And the user selecting the Create Record screen Create button 
	Then the user verifying the presence of Record Creation Success screen 

	Scenario: User navigating to the created record 
	Given the user moving to the record from record creation success screen 
	Then the user verifying the presence of Record screen 

	Scenario: User launching the Sample Workflow RateAI 
	Given the user clicking on View all workflow Grid
	Then the user running "Third" workflow from the view all workflows screen
	And the user verifying that the selected workflow "Sample Workflow RateAI" is opened

	Scenario: User launching the task RateAI from workflow screen	
	Given the user selecting the task "RateAI" from task list
	Then the user verifying that the selected task "RateAI" is opened

	Scenario: User Uploading Truck shipper file 
	Given the user selecting Shipper Import File button 
	Then the user uploading the shipper file "Lowes_Mini_Example.xlsx" in Price Import screen 
    And the user clicking the "Import" button 
	Then the user verifying that the Header row prediction is "6" 
	And the user clicking the "Proceed" button
	And the user clicking the "CloseSubmissionButton" button
	And the user clicking the "File upload history table_Expand_Edit" button
	And the user verifying that the status changed to "Confirm Mapping" in File upload history table 

Scenario: User verifying the initial review screen 
	Given the user clicking the "Confirm Mapping" button
	Then the user verifying the presence of Initial Review screen
	And the user verifying all the mapped Excel columns in Initial Review screen for "Lowes_Mini_Example.xlsx"
	
Scenario: User verifying the Adjust Auto-Mapped screen 
	Given the user clicking the "Mapping Screen Next" button 
	Then the user verifying the presence of Adjust Auto-Mapped screen
	And the user verifying all the mapped Excel columns header in Confirm Mapping screen for "Lowes_Mini_Example.xlsx"
	
	Scenario: User verifying the navigation to Adjust Unmapped screen 
	Given the user clicking the "Mapping Screen Next" button 
	Then the user verifying the presence of Adjust Unmapped screen 

	Scenario: User updating the adhoc columns in Adjust Unmapped screen
	Given the user clicking on the button import as AdHoc column_CheckBox "1"
	Then the user updating the adhoc column "1" segment selection dropdown value as "None"
	And the user updating the adhoc column "1" adhoc type as "Other"
	Then the user clicking on the button import as AdHoc column_CheckBox "2"
	And the user updating the adhoc column "2" segment selection dropdown value as "None"
	Then the user updating the adhoc column "2" adhoc type as "Other"
	And the user updating the "second table" column name to "adhoc dest hour" in Map Unmatched Screen


	Scenario: User verifying the Mapping review screen 
	Given the user clicking the "Mapping Screen Next" button 
	Then the user verifying the presence of Mapping Review screen

	Scenario: User verifying the File upload status in File upload history session 
	Given the user selecting the Mapping Screen Proceed button 
	And the user clicking the "CloseSubmissionButton" button
	And the user clicking the "File upload history table_Expand_Edit" button
	Then the user verifying that the status changed to Completed in File upload history table

	Scenario: User verifying the PI table view 
	Given the user clicks on the "PI table _Expand" button
	Then the user verifying the imported data row count from PI table for "Lowes_Mini_Example"

	Scenario: User enabling the shipper write back for Destination City column from PI Table
		Given the user selecting the 3dot option for the column "Destination City" in PI Table
		Then the user clicks on the button "Add to Shipper Update_3Dot Option"

	Scenario: User verifying that the AdHoc columns are showing in the PI table
	Given the user verifying that the PI table is showing the column "Origin Hours"
	Then the user verifying that the PI table is showing the column "adhoc dest hour"