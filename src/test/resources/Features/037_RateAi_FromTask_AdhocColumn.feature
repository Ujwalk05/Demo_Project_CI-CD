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

    # Scenario: User creating a record
	# Given the user selecting the "Browse" tab 
	# Then the user navigating to the "Bids" options 
	# And the user selecting the Create Record button 
	# Then the user adding details in Create Record screen for creating a record with name "Rate AI Task Shipper Adhoc Column testing" 
	# And the user selecting the Create Record screen Create button 
	# Then the user verifying the presence of Record Creation Success screen 

# 	Scenario: User navigating to the created record 
# 	Given the user moving to the record from record creation success screen 
# 	Then the user verifying the presence of Record screen 

# 	Scenario: User launching the Sample Workflow RateAI 
# 	Given the user clicking on View all workflow Grid
# 	Then the user running "Third" workflow from the view all workflows screen
# 	And the user verifying that the selected workflow "Sample Workflow RateAI" is opened

    Scenario: User navigating to the created record
	And the user selecting the "Browse" tab 
	Then the user navigating to the "Bids" options 
	And the user searching for a Record "Rate AI Task Shipper Adhoc Column testing" 
	Then the user verifying the record search result for "Rate AI Task Shipper Adhoc Column testing" 
	And the user selecting the record from search result 


	# Scenario: User launching the task RateAI from workflow screen	
	# Given the user selecting the task "RateAI" from task list
	# Then the user verifying that the selected task "RateAI" is opened

# 	Scenario: User Uploading Truck shipper file 
# 	Given the user selecting Shipper Import File button 
# 	Then the user uploading the shipper file "Lowes_Mini_Example.xlsx" in Price Import screen 
#     And the user clicking the "Import" button 
# 	Then the user verifying that the Header row prediction is "6" 
# 	And the user clicking the "Proceed" button
# 	And the user clicking the "CloseSubmissionButton" button
# 	And the user clicking the "File upload history table_Expand_Edit" button
# 	And the user verifying that the status changed to "Confirm Mapping" in File upload history table 

# Scenario: User verifying the initial review screen 
# 	Given the user clicking the "Confirm Mapping" button
# 	Then the user verifying the presence of Initial Review screen
# 	And the user verifying all the mapped Excel columns in Initial Review screen for "Lowes_Mini_Example.xlsx"
	
# Scenario: User verifying the Adjust Auto-Mapped screen 
# 	Given the user clicking the "Mapping Screen Next" button 
# 	Then the user verifying the presence of Adjust Auto-Mapped screen
# 	And the user verifying all the mapped Excel columns header in Confirm Mapping screen for "Lowes_Mini_Example.xlsx"
	
# 	Scenario: User verifying the navigation to Adjust Unmapped screen 
# 	Given the user clicking the "Mapping Screen Next" button 
# 	Then the user verifying the presence of Adjust Unmapped screen 

# 	Scenario: User updating the adhoc columns in Adjust Unmapped screen
# 	Given the user clicking on the button import as AdHoc column_CheckBox "1"
# 	Then the user updating the adhoc column "1" segment selection dropdown value as "None"
# 	And the user updating the adhoc column "1" adhoc type as "Other"
# 	Then the user clicking on the button import as AdHoc column_CheckBox "2"
# 	And the user updating the adhoc column "2" segment selection dropdown value as "None"
# 	Then the user updating the adhoc column "2" adhoc type as "Other"
# 	And the user updating the "second table" column name to "adhoc dest hour" in Map Unmatched Screen


# 	Scenario: User verifying the Mapping review screen 
# 	Given the user clicking the "Mapping Screen Next" button 
# 	Then the user verifying the presence of Mapping Review screen

# 	Scenario: User verifying the File upload status in File upload history session 
# 	Given the user selecting the Mapping Screen Proceed button 
# 	And the user clicking the "CloseSubmissionButton" button
# 	And the user clicking the "File upload history table_Expand_Edit" button
# 	Then the user verifying that the status changed to Completed in File upload history table

	# Scenario: User verifying the PI table view 
	# Given the user clicks on the "PI table _Expand" button
	# Then the user verifying the imported data row count from PI table for "Lowes_Mini_Example"

	# Scenario: User enabling the shipper write back for Destination City column from PI Table
	# 	Given the user selecting the 3dot option for the column "Destination City" in PI Table
	# 	Then the user clicks on the button "Add to Shipper Update_3Dot Option"

	# Scenario: User verifying that the AdHoc columns are showing in the PI table
	# Given the user verifying that the PI table is showing the column "Origin Hours"
	# Then the user verifying that the PI table is showing the column "adhoc dest hour"

	# Scenario: User opening the Price edit modal from PI table and verifying the adhoc tab
	# Given the user clicks on the button "PI Table 1st Row Expand"
	# Then the user verifying that the Price Edit modal showing the "Ad Hoc tab"
	# And the user clicking on the Tab "ad hoc tab" in Price Edit Modal
	# And the user verifying that the Price Edit modal showing the "Origin Hours Column"
	# Then the user verifying that the Price Edit modal showing the "adhoc dest hour Column"

	# Scenario: User updating the Origin Hours value from price edit modal
	# Given the user updating the "Origin Hours" value from Price Edit modal screen

	# Scenario: User closing the PI
	# Given the user clicks on the button "Pricing Modal Close" 
	# And the user clicks on the button "PI table _Close"

	# Scenario: User closing the task RateAI
	# Given the user selecting the "Task view close" icon 

	Scenario: User launching the task RateAi Adhoc from workflow screen
		Given the user selecting the task "RateAI Adhoc" from task list
		Then the user verifying that the selected task "RateAI Adhoc" is opened

	Scenario: User verifying the Pricing Origin Hours Edited value in PI table
	Given the user clicks on the "PI table _Expand" button
	And the user verifying that the PI table is showing the column "Origin Hours Edited value from Pricing Modal"

	# Scenario: User doing batch update for the adhoc dest hour column in PI Table  and Pricing Status column in PI Table
	# Given the user clicks on the button "PITable_SelectAll_Checkbox"
	# When the user clicks on the button "Batch Update in PI Table"
	# And the user verifying that the "Batch Update" view is opened
	# And the user selecting the "adhoc dest hour" field and giving New Value "09:00" in Batch Update View
	# And the user clicks on the "Update in Batch Update View" button
	# And the user clicks on the button "Done in Batch update success pop up"
    # Then the user verifying that updated value is "09:00" for "adhoc dest hour column"

	


	# Scenario: User doing batch update for the Pricing Status column in PI Table
	# #Given the user clicks on the button "PITable_SelectAll_Checkbox"
	# When the user clicks on the button "Batch Update in PI Table"
	# Then the user verifying that the "Batch Update" view is opened
	# And the user selecting the "Pricing Status" field and giving New Value "Ready" in Batch Update View
	# And the user clicks on the "Update in Batch Update View" button
	# Then the user clicks on the button "Done in Batch update success pop up"
	# Then the user verifying that updated value is "Ready" for "Pricing Status column"

	Scenario: User Exporting the excel from PI table
	# Given the user clicks on the "PI table _Close" button
	# When the user clicks on the "PI table _Expand" button
	And the user verifying the imported data row count from PI table for "Lowes_Mini_Example"
	And the user clicks on the "PI Table Export Excel" button
	Then the user setting export file name as "tl_pricing_bid_set"
	And the user clicks on the "PI Table Export Excel Download" button
	And the user clicks on the "Download Modal Close" button
	Then the user verifying that the "tl_pricing_bid_set.xlsx/tl_pricing" file downloaded successfully
	
	Scenario: User verifying that the Adhoc columns are present in the exported excel from Task screen PI Table
	Given the user verifying that the "Origin Hours" column present in the file "tl_pricing_bid_set"
	And the user verifying that the "adhoc dest hour" column present in the file "tl_pricing_bid_set"
	Then the user deleting "tl_pricing_bid_set.xlsx" file from directory


    Scenario: User selecting the Create Submission to generate the submission file
	Given the user clicks on the "PI table _Close" button 
	When the user clicking on the button "Submission Action"
	And the user clicking on the button "Create Submission" 
	Then the user clicking on the button "Proceed in Submission Confirmation"

	# Scenario: User navigating to the created record
	# And the user selecting the "Browse" tab 
	# Then the user navigating to the "Bids" option 
	# And the user searching for a Record "Rate AI Task Shipper Adhoc Column testing" 
	# Then the user verifying the record search result for "Rate AI Task Shipper Adhoc Column testing" 
	# And the user selecting the record from search result 
