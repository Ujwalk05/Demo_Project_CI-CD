@QuickCreate @SmokeSet1
Feature: Quick Create related testing

Scenario: Launching the app
	Given the user navigates to "Winmore" application
	Then the user verifying the presence of login page

Scenario: Login into the app
	Given the user logging into the app
 		| Email 					| password		|
		| automationtester@winmore.app		| Test@123		|
	And the user selecting the Organization "Engineering Automation" 
	Then the user verifying the successful login 
	
Scenario: User checking the schedule activity creation
	Given the user navigating to the "Quick Create" option in homepage
	#Then the user selecting "Schedule Activity" option from Quick Create list
	#And the user verifying that the "Schedule" activity screen in quick create is opened
	#Then the user adding details in Activity Qucik Create screen
	#And the user selecting the "Quick Create Save" button
	#Then the user verifying the Activity creation completion
	