@ImproveShipperImport @SmokeSet1 @RegressionSet1
Feature: Verifying the Shipper Import

  Scenario: Launching the app
	Given the user navigates to "Winmore" application
	Then the user verifying the presence of login page
	
  Scenario: Login into the app
	Given the user logging into the app
 		| Email 					| password		|
		| automationtester@winmore.app		| Test@123		|
	And the user selecting the Organization "RateAI CurrencyAndFormula Automation" 
	Then the user verifying the successful login
	
  Scenario: User is creating a record
    Given the user selecting the "Browse" tab
    Then the user navigating to the RateAILatest option
    And the user selecting the Create Record button
    Then the user adding record name "ImproveShipperImport" in Create Record screen
    And the user selecting the Create button
    Then the user verifying the presence of Record Creation Success screen
    
  Scenario: User navigating to the newly created record
    Given the user moving to the record from record creation success screen
    Then the user verifying the presence of Record screen
    
  Scenario: User navigating to the Ocean Pricing page in template screen 
	Given the user selecting the "Ocean Pricing" template from record screen 
	Then the user verifying the presence of "Ocean Pricing" template screen
	
  Scenario: User Uploading the shipper file 
	Given the user selecting the "Import File" button in the opean pricing page
	Then the user uploading the shipper file "1.AVERY.DENNISON.-.OdsFormat.ods" from "TestUploadingFiles" folder in Price Import screen 
	#And the user clicking the "Import" button
	
  Scenario: User Verifying the Error Warning Modal
    Then the user verifying the presence of "File should be in XLS, XLSX, XLSM or XLSB format." template screen under ocean pricing
    And the user selecting the "OK" button under ocean pricing