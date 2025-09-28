@Login @SmokeSet1 @RegressionSet1
Feature: Login related testing

  Scenario: Launching the app
	Given the user navigates to "Winmore" application
	Then the user verifying the presence of login page
	
  Scenario: Login into the app
	Given the user logging into the app
 		| Email 					| password		|
		| automationtester@winmore.app		| Test@123		|
	And the user selecting the Organization "Engineering Automation" 
	Then the user verifying the successful login
	Then the user verifying the title of the homepage
	
  Scenario: User logging out from the app
	Given the user navigating to the "Profile Image" option
	Then the user selecting the "Logout" button
	And the user verifying the presence of login page