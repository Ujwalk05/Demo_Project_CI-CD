@ExtensionPart2 @SmokeSet1 @RegressionSet1 @AutoExportDoc
Feature: Extension Auto Export Doc related testing

  Scenario: Launching the app
    Given the user navigates to "Winmore" application
    Then the user verifying the presence of login page

  Scenario: Login into the app
    Given the user logging into the app
      | Email                 | password |
      | automationtester@winmore.app | Test@123 |
    And the user selecting the Organization "Automating Extension"
    Then the user verifying the successful login

  Scenario: User creating a record
    Given the user selecting the "Browse" tab
    Then the user navigating to the "Aggregate Shipments" option in browse
    And the user selecting the "Create Record" button
    Then the user selecting the "Cancel & Back" button
    
  Scenario: User logging out from the app
	Given the user navigating to the "Profile Image" option
	Then the user selecting the "Logout" button
	And the user verifying the presence of login page
    
