package com.winmore.pages;

public class ShipperFileImportpage {
	
	public String rowNumber = "//input[@placeholder='Enter row number here']";
	public String rowNumber_Description = "Input field to enter row number";
	
	public String errorMessageForFile = "//div[.='File should be in XLS, XLSX, XLSM or XLSB format.']";
	public String errorMessageForFile_Description = "Error message on uploading non-supporting files";

	public String okButton = "//button[.='%s']";
	public String okButton_Description = "OK Button";
	
	public String enterRowNumber = "//tr[@class='client-components-table-renderer-components-modal-upload-pricing-component-sheet-selection-__sheet-selection-module__selected']//input[@placeholder='Enter row number here']/..//input";
	public String enterRowNumber_Description = "Row number input field inside selected sheet row";

	public String importFile = "//*/text()[normalize-space(.)='Import File']/parent::*";
	public String importFile_Description = "Button to import file in workbench";

	public String fileUploadModal = ".dropzone-empty";
	public String fileUploadModal_Description = "Dropzone area in file upload modal";

	public String priceImport = "//div[@class='client-components-table-renderer-components-modal-upload-pricing-component-__header-module__paddedContainer']//span[contains(text(),'Import')]";
	public String priceImport_Description = "Import button in price import modal header";

	public String proceImportProceed = "//div[@class='client-components-table-renderer-components-modal-upload-pricing-component-__header-module__paddedContainer']//span[contains(text(),'Proceed')]";
	public String proceImportProceed_Description = "Proceed button in price import modal header";

	public String closeSubmissionButton = "//div[@class='client-components-generic-progress-notification-modal-__index-module__modal']//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white']";
	public String closeSubmissionButton_Description = "Close button (X icon) to dismiss submission modal";

	public String ConfirmMappingStatus_InFileUploadHistoryTable= "//div[@class='react-grid-Cell__value']//a[contains(text(),'Confirm Mapping')]";
	public String ConfirmMappingStatus_InFileUploadHistoryTable_Description= "Confirm Mapping status link in File Upload History table";

	public String ExpandAndEditBtn_FileUploadHistoryTable = "(//button[@class='-u-btn -u-btn--pill']//span[contains(text(),'Expand and Edit')])[1]";
	public String ExpandAndEditBtn_FileUploadHistoryTable_Description= "Expand and Edit button in File Upload History table";

	public String MappingScreen_NextButton = "//div[@class='mapping-header-buttons']//span[contains(text(),'Next')]";
	public String MappingScreen_NextButton_Description = "Next button in Mapping screen header";

	


}
