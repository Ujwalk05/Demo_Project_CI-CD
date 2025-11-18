package com.winmore.pages;

public class HomePage {
	
	public String quickCreateBtn = "//a[@class='site-nav-anchor icon icon-quicklaunch']";
	public String quickCreateBtn_description = "A button or link with 'quicklaunch' icon class for quick creation.";

	public String browseButton = "//a[normalize-space()='Browse']";
	public String browseButton_Description = "Browse button";
	
	public String rateAIAutos = "//a[normalize-space()='RateAIAutos']";
	public String rateAIAutos_Description = "RateAIAutos button";

	public String bids= "//a[normalize-space()='Bids']";
	public String bids_Description = "Bids button";

	public String rateAILatest = "//a[contains(text(),'RateAI Latest')][1]";
	public String rateAILatest_Description = "RateAILatest button";
	
	public String closeButton = "//a[@class='site-nav-anchor icon icon-quicklaunch']";
	public String closeButton_Description = "Close button in site navigation";
	
	public String cancelAndBack = "//span[contains(text(),'Cancel & Back')]";
	public String cancelAndBack_Description = "Cancel And Back Button";

	public String createRecord = "//span[@class='-u-btn__content']//span[contains(text(),'Create Record')]";
	public String createRecord_Description = "Create Record button";

    public String RecordCreate = "//div[@class='slideout-header -u-flex--none']//span[contains(text(),'Create')]";
	 public String RecordCreate_description = "Create button in slideout header";
	 
	public String addRecordName = "(//ul[@class='flexible-list list-unstyled layout-group']//input[@class='-u-form-group__input -u-form-group__input--text'])[1]";
	public String addRecordName_Description = "First input field for entering record name";

	public String pageHeaderTitle = "//h2[@class='pageheader__title']";
	public String pageHeaderTitle_Description = "Page header title element";

	public String createButton = "//div[@class='slideout-header -u-flex--none']//span[contains(text(),'Create')]";
	public String createButton_Description = "Create button in slideout header";

	public String createRecordSuccess = "(//div[@class='record-status-button-container']//button[@class='-u-btn -u-flex--none -u-btn--pill -u-btn--block'])[1]";
	public String createRecordSuccess_Description = "Record created successfully status button (first one)";

	public String settingsGearButton = "//i[@class='-u-icon -u-icon--gear -u-flex--none']";
	public String settingsGearButton_Description = "Settings gear icon button";

	public String deleteRecord = "//li[@class='list-group-item']//span[contains(text(), 'Delete' )]";
	public String deleteRecord_Description = "Delete record option from list";
	
	public String aggregateShipment = "//a[contains(text(),'%s')][1]";
	public String aggregateShipment_Description = "Aggregate Shipment Option";

	public String Workbench = "//a[@class='app-item selected']/../a[.='%s']";
	public String Workbench_Description = "Template option in record screen (replace %s with template name)";

	public String confirmDeleteRecord = "//div[@class='confirm-slideout u-confirm-slideout']//a[contains(text(),'Delete')]";
	public String confirmDeleteRecord_Description = "Confirm delete record button in confirmation popup";


	public String UserAccount= "//label[text()='Account']/following-sibling::div//input[1]";
	public String UserAccount_Description = "User Account field in the creation slideout";


	public String BidDate= "(//div[@class='datepicker-container']//button)[1]";
	public String BidDate_Description = "Bid Date field in the creation slideout";

	public String CalenderNextMonthBtn_InCreateRecordScreen = " //div[@class='date-picker-wrapper']//div[@class='dp-next-nav dp-nav-cell dp-cell'] | //div[@class=' date-picker']//div[@class='dp-next-nav dp-nav-cell dp-cell']";
	public String CalenderNextMonthBtn_InCreateRecordScreen_Description = "Next month button in calendar popup in create record screen";

    public String CalenderDate_4thRaw4col_InCreateRecordScreen = " //div[@class='dp-body']//div[@class='dp-week dp-row'][4]//div[@class='dp-cell dp-day'][4]";
	public String CalenderDate_4thRaw4col_InCreateRecordScreen_Description = "4th row 4th column date in calendar popup in create record screen";

	public String ScopeSelectionDropdownInputInCreationLayout = "//label[text()='Scope']/following-sibling::div//input[1]";
	public String ScopeSelectionDropdownInputInCreationLayout_Description = "Scope dropdown input field in creation layout";

    public String ModeSelectionDropdownInputInCreationLayout = "//label[text()='Mode']/following-sibling::div//input[1]";
	public String ModeSelectionDropdownInputInCreationLayout_Description = "Mode dropdown input field in creation layout";

	public String CommoditySelectionDropdownInputInCreationLayout = "//label[text()='Commodity']/following-sibling::div//input[1]";
	public String CommoditySelectionDropdownInputInCreationLayout_Description = "Commodity dropdown input field in creation layout";

    public String BrowseComponentRecordList = "//div[@class='client-apps-browse-containers-__content-module__items']";
    public String BrowseComponentRecordList_description = "The container for a list of records in the browse component.";

    public String FilteringConditions_SelectControllingSearchField = "//div[@class='-u-form-group -u-form-group--block']//div[@class='-u-form-group -u-form-group--block']//input[@placeholder='Search']";
    public String FilteringConditions_SelectControllingSearchField_description = "A search input field for selecting a controlling field.";

    public String BrowseComponentRecordSearchResult = "//div[@class='client-apps-browse-components-records-__list-item-module__title']/div";
    public String BrowseComponentRecordSearchResult_description = "A container for the title of a record search result in the browse component.";

}
