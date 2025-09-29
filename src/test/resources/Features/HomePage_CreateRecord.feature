@CreateRecord @SmokeSet1 @RegressionSet1
Feature: Create Record and its deleting related testing

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
	
  Scenario: User is creating a record
    Given the user selecting the "Browse" tab
    Then the user navigating to the RateAIAutos option
    And the user selecting the Create Record button
    Then the user adding record name "New Rate AI Project" in Create Record screen
    And the user selecting the Create button
    Then the user verifying the presence of Record Creation Success screen
    
  Scenario: User navigating to the newly created record
    Given the user moving to the record from record creation success screen
    Then the user verifying the presence of Record screen
    
  Scenario: Deleting the created record
    Given the user selecting the Record screen Settings Gear button
    And the user selecting the Record Delete button
    Then the user selecting the Confirm Record Delete button
    
  Scenario: User logging out from the app
	Given the user navigating to the "Profile Image" option
	Then the user selecting the "Logout" button
	And the user verifying the presence of login page
    