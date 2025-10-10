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

    Scenario: User creating a record for Formula Check from PI
	Given the user selecting the "Browse" tab 
	Then the user navigating to the "Bids" options 
	And the user selecting the Create Record button 
	Then the user adding details in Create Record screen for creating a record with name "Rate AI Task Shipper" 
	And the user selecting the Create Record screen Create button 
	Then the user verifying the presence of Record Creation Success screen 

	Scenario: User navigating to the created record 
	Given the user moving to the record from record creation success screen 
	Then the user verifying the presence of "Record" screen 

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
	Then the user verifying that the Header row prediction is "4" 
	And the user clicking the "Proceed" button
	And the user clicking the "CloseSubmissionButton" button

