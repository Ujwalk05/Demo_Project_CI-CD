package com.winmore.pages;

public class OceanPricingPage {
	
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

	public String closeSubmissionButton = "(//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white'])[last()]";
	public String closeSubmissionButton_Description = "Close button (X icon) to dismiss submission modal";

}
