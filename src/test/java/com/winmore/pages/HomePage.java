package com.winmore.pages;

public class HomePage {
	
	public String quickCreateBtn = "//a[@class='site-nav-anchor icon icon-quicklaunch']";
	public String quickCreateBtn_description = "A button or link with 'quicklaunch' icon class for quick creation.";

	public String browseButton = "//a[normalize-space()='Browse']";
	public String browseButton_Description = "Browse button";
	
	public String rateAIAutos = "//a[normalize-space()='RateAIAutos']";
	public String rateAIAutos_Description = "RateAIAutos button";
	
	public String closeButton = "//a[@class='site-nav-anchor icon icon-quicklaunch']";
	public String closeButton_Description = "Close button in site navigation";
	
	public String cancelAndBack = "//span[contains(text(),'Cancel & Back')]";
	public String cancelAndBack_Description = "Cancel And Back Button";

	public String createRecord = "//span[@class='-u-btn__content']//span[contains(text(),'Create Record')]";
	public String createRecord_Description = "Create Record button";
	
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

	public String oceanPricingElement = "//a[@class='app-item selected']/../a[.='%s']";
	public String oceanPricingElement_Description = "Template option in record screen (replace %s with template name)";

	public String confirmDeleteRecord = "//div[@class='confirm-slideout u-confirm-slideout']//a[contains(text(),'Delete')]";
	public String confirmDeleteRecord_Description = "Confirm delete record button in confirmation popup";

}
