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