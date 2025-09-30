@RateAI @PISelectedDownload @RegressionSet1 @SmokeSet1
Feature: RateAI_ PI Selected download from record related test cases 

Scenario: Launching the app
	Given the user navigates to "Winmore" application
	Then the user verifying the presence of login page

Scenario: Login into the app
	Given the user logging into the app
 		| Email 						| password	|
		| rahul.raman+auto2@winmore.app	| Test@123	|
	#And the user selecting the Organization "Automation Scripting" 
	Then the user verifying the successful login
	
Scenario: User creating a record for Formula Check from PI
	Given the user selecting the "Browse" tab
	Then the user navigating to the "RateAIAutos" options
	And the user selecting the Create Record button
	Then the user adding record name "PI Selected New Download" in Create Record screen
	And the user selecting the Create button
    Then the user verifying the presence of Record Creation Success screen
    
Scenario: User navigating to the newly created record
    Given the user moving to the record from record creation success screen
    Then the user verifying the presence of Record screen
    And the user verifying the "Overview" from Record overview screen
    
Scenario: User navigating to the Ocean Pricing page in template screen 
	Given the user selecting the "Ocean Pricing" template from record screen 
	Then the user verifying the presence of "Ocean Pricing" template screen
	
Scenario: User Uploading the shipper file 
	Given the user selecting the "Import File" button in the opean pricing page
	Then the user uploading the shipper file "KimberlyClark_FormulaCheckFile.xlsx" in Price Import screen 
	And the user clicking the "Import" button 
	Then the user verifying that the Header row prediction is "4" 
	And the user clicking the "Proceed" button
	And the user clicking the "CloseSubmissionButton" button
	
Scenario: User logging out from the app
	Given the user navigating to the "Profile Image" option
	Then the user selecting the "Logout" button
	And the user verifying the presence of login page
	