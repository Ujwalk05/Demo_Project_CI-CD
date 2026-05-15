package com.winmore.pages;

public class PriceInspectorPage {
    
    public String PI_Table_Expand_Btn = "(//button[@class='-u-btn -u-btn--pill']//span[contains(text(), 'Expand')])[3]";
    public String PI_Table_Expand_Btn_Description = "Expand button in Price Inspector table";

    public String PITableImportedDataRowCounts_PaginationPITable = "//div[@class='lxtable__pagination__count']";
    public String PITableImportedDataRowCounts_PaginationPITable_Description = "Imported Data Row Counts in Pagination of PI Table";

    public String PI_Column_Header_Custom_Xpath_New = "//p[.='%s']";
    public String PI_Column_Header_Custom_Xpath_New_Description = "Dynamic locator for PI column header based on column name";

    public String PITable_Column_3DotOption_AddToShipperUpdate = "//div[@class='-u-list-item__content -u-flex--one -u-flex--row -u-flex--align-self--center']//div[.='Add to Shipper Update']";
    public String PITable_Column_3DotOption_AddToShipperUpdate_Description = "Add to shipper update option in the list";

    public String PITable_ColumnHeader_OriginHours = "//div[contains(text(),'Origin Hours')] | //p[normalize-space()='Origin Hours']";
    public String PITable_ColumnHeader_OriginHours_Description = "Origin Hours column header in PI table";

    public String PITable_ColumnHeader_AdhocDestHour = "//div[contains(text(),'adhoc dest hour')] | //p[normalize-space()='adhoc dest hour']";
    public String PITable_ColumnHeader_AdhocDestHour_Description = "Adhoc Dest Hour column header in PI table";

    public String PITable_1stRow_Expand_btn = "(//i[@class='-u-icon -u-icon--expand'])[1]";
    public String PITable_1stRow_Expand_btn_description = "The pitable 1st row expand btn element.";

    public String PriceEditModal_AdHoc_Tab = "//div[text() = 'Ad Hoc']";
    public String PriceEditModal_AdHoc_Tab_description = "The price edit modal ad hoc tab for 'Ad Hoc'.";

    public String PriceEditModal_OriginHoursLabel = "//label[text() = 'Origin Hours']";
    public String PriceEditModal_OriginHoursLabel_description = "The price edit modal origin hours label for 'Origin Hours'.";
   
    public String PriceEditModal_AdHocDestHourLabel = "//label[text() = 'adhoc dest hour']";
    public String PriceEditModal_AdHocDestHourLabel_description = "The price edit modal ad hoc dest hour label for 'adhoc dest hour'";

    public String PITable_Column_3DotOption_Button = "//div[@class='multisort multisort-hover']//div[@class='multisort-menu']/button | //div[@class='multisort multisort-hover']//div[@class='multisort-menu']//button | //div[@class='multisort multisort-hover']/div[@class='multisort-menu']/descendant::button[1]";
    public String PITable_Column_3DotOption_Button_description = "The pitable column 3dot option button element.";

   public String PriceEditModal_OriginHours_ValueField = "//label[text() = 'Origin Hours']/..//input[@class='-u-form-group__input -u-form-group__input--text']";
   public String PriceEditModal_OriginHours_ValueField_description = "The price edit modal origin hours value field for 'Origin Hours'.";

   public String PriceEditModal_Updated_label = "//div[@class='client-components-table-renderer-components-modal-component-header-__header-module__flex']//span[contains(text(),'Updated')] | //div[@class='client-components-table-renderer-components-modal-component-header-__header-module__flex']//span[contains(text(),'Saved')]";
   public String PriceEditModal_Updated_label_description = "The price edit modal updated label for 'Updated'.";

   public String PriceEditModal_CloseBtn = "(//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white'])[1]";
   public String PriceEditModal_CloseBtn_description = "The price edit modal close btn element.";

  public String PITable_CloseBtn = "//button[@class='-u-btn -u-btn--pill -u-btn--block']//span[contains(text(),'Close')]";
  public String PITable_CloseBtn_description = "The pitable close btn for 'Close'.";

  public String PITable_OriginHoursFirstRow_EditedValueFromPriceEditModal = "//span[contains(text(),'06:00-16:30 M-F')]";
  public String PITable_OriginHoursFirstRow_EditedValueFromPriceEditModal_description = "The PI table origin hours first row edited value from price edit modal.";

  public String PITable_selectAllCheckbox = "//input[@name='selectAllCheckbox'] | //input[@title='Toggle All Rows Selected'] | (//input[@type='checkbox'])[1]";
  public String PITable_selectAllCheckbox_description = "the pi table select all checkbox element.";

  public String PITableSavingIndicator = "//div[@class='lxtable--full__header__saving client-components-saving-indicator-__index-module__container']//span[normalize-space()='Saved'] | //div[@class='lxtable--full__header__saving client-components-saving-indicator-__index-module__container']//span[normalize-space()='Updated']";
  public String PITableSavingIndicator_description = "The PI table saving indicator element showing 'Saved' or 'Updated'.";

 public String PITableSavingInProgressIndicator = "//div[@class='lxtable--full__header__saving client-components-saving-indicator-__index-module__container']//span[normalize-space()='Saving']";
 public String PITableSavingInProgressIndicator_description = "The PI table saving indicator element showing 'Saving'.";

  public String PITable_BatchUpdateBtn = "//button//span[contains(text(),'Batch Update')]";
  public String PITable_BatchUpdateBtn_description = "The PI table batch update button element.";

  public String BatchUpdateView_Header = "//div[@class='client-components-table-renderer-components-batch-update-modal-__index-module__modal']//h1";
  public String BatchUpdateView_Header_description = "The batch update view header element.";

  public String BatchUpdateFieldOption_adhocDestHour = "//ul[@class='list-group type-list']//li//a[contains(text(),'adhoc dest hour')] | //ul[@class='slideout-contents list-group type-list']//li//a[contains(text(),'adhoc dest hour')]";
  public String BatchUpdateFieldOption_adhocDestHour_description = "The batch update view field option for 'adhoc dest hour'.";

  public String Batchupdate_selectField = "//button[.='Select field']";
  public String Batchupdate_selectField_description = "The batch update view select field dropdown button element.";

  public String BatchUpdate_NewValueInput_Field = "//input[@class='-u-form-group__input -u-form-group__input--text']";
  public String BatchUpdate_NewValueInput_Field_description = "The batch update view new value input field element.";
 
  public String BatchUpdateView_UpdateBtn = "//div[@class='client-components-table-renderer-components-batch-update-modal-__index-module__modal']//button//span[contains(text(),'Update')]";
  public String BatchUpdateView_UpdateBtn_description = "The batch update view update btn for 'Update'.";

  public String BatchUpdateRecordWereUpdatedMessage = "//p[contains(text(),'were updated.')]";
  public String BatchUpdateRecordWereUpdatedMessage_description = "The batch update record were updated message for 'were updated.'.";

  public String BatchUpdateCompletedSuccessfullyMessage = "//div[contains(@class,'client-components-generic-progress-notification-modal-__index-module__wrapper')]//div[normalize-space()='Batch update completed successfully.']";
  public String BatchUpdateCompletedSuccessfullyMessage_description = "The batch update completed successfully message for 'Batch update completed successfully.'.";

  public String BatchUpdate_SuccessPopup_DoneBtn = "//button//span[contains(text(),'Done')]";
  public String BatchUpdate_SuccessPopup_DoneBtn_description = "The batch update success popup done btn for 'Done'.";

  public String BatchUpdate_Modal_CloseBtn = "//div[@class='client-components-table-renderer-components-batch-update-modal-__index-module__modal']//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white'] | //div[@class='client-components-generic-progress-notification-modal-__index-module__modal']//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white']";
  public String BatchUpdate_Modal_CloseBtn_description = "The batch update modal close btn element.";

  public String BatchUpdateInProgressMessage = "//div[contains(@class,'client-components-generic-progress-notification-modal-__index-module__wrapper')]//div[normalize-space()='Batch update in progress.']";
  public String BatchUpdateInProgressMessage_description = "The batch update in progress message for 'Batch update in progress.'.";

  public String BatchUpdateFieldOption_PricingStatus = "//ul[@class='list-group type-list']//li//a[contains(text(),'Pricing Status')] | //ul[@class='slideout-contents list-group type-list']//li//a[contains(text(),'Pricing Status')]";
  public String BatchUpdateFieldOption_PricingStatus_description = "The batch update field option pricing status for 'Pricing Status'.";

  public String BatchUpdate_PricingStatus_OptionsListed_ToPrice = "//ul[@class='list-group slideout-contents']//li//span[normalize-space()='To Price'] | //ul[@class='list-group slideout-contents']//li//span[text()[contains(.,'To Price')]]";
  public String BatchUpdate_PricingStatus_OptionsListed_ToPrice_description = "The batch update pricing status options listed to price for 'To Price'.";
  public String BatchUpdate_PricingStatus_OptionsListed_Ready = "//ul[@class='list-group slideout-contents']//li//span[normalize-space()='Ready'] | //ul[@class='list-group slideout-contents']//li//span[text()[contains(.,'Ready')]]";  
  public String BatchUpdate_PricingStatus_OptionsListed_Ready_description = "The batch update pricing status options listed ready for 'Ready'.";
  public String BatchUpdate_PricingStatus_OptionsListed_AwardedPrimary = "//ul[@class='list-group slideout-contents']//li//span[normalize-space()='Awarded - Primary']";
  public String BatchUpdate_PricingStatus_OptionsListed_AwardedPrimary_description = "The batch update pricing status options listed awarded primary for 'Awarded - Primary'.";

  public String BatchUpdate_NewValue_SelectionField = "//div[@class='client-components-table-renderer-components-batch-update-modal-__index-module__modal']//button[@class='-u-btn -u-btn--picker -u-form-group__input -u-btn--block']//span[@class='-u-ellipsis']";
  public String BatchUpdate_NewValue_SelectionField_description = "The batch update new value selection field element.";

  public String PITable_adhocDestHour_FirstRow_BatchUpdatedValue = "(//span[contains(text(),'09:00')])[1]";
  public String PITable_adhocDestHour_FirstRow_BatchUpdatedValue_description = "The PI table adhoc dest hour first row batch updated value for '09:00'.";
  
  public String PITable_PricingStatusUpdatedvalue = "(//span[contains(text(),'Ready')])[1]";
  public String PITable_PricingStatusUpdatedvalue_description = "Pi table updated value of Pricing status column";

  public String PITable_Export_Excel_Btn = "//div[@class='lxtable--full__header__options']//button//i[@class='-u-icon -u-icon--excel -u-flex--none']";
  public String PITable_Export_Excel_Btn_description ="The pitable export excel btn element.";

 public String CancelBtn_slideout = "//span[contains(text(),'Cancel')]";
 public String CancelBtn_slideout_description = "The rule cancel button in rule3dot option for 'Cancel'.";
 
 public String FileNameInputFieldInPIDownload= "//div[text()='File Name']/following::input[1]";
 public String FileNameInputFieldInPIDownload_description = "The file name input field in pidownload for 'File Name'.";

 public String PITable_Export_Excel_Download_Btn = "//button//span[contains(text(),'Download')]|//button//div[contains(text(),'Download')]";
 public String PITable_Export_Excel_Download_Btn_description = "The pitable export excel download btn for 'Download'.";

 public String PI_File_Generation_Modal="//span[text()='Price Inspector Download']";
 public String PI_File_Generation_Modal_description = "The pi file generation modal for 'Price Inspector Download'.";

 public String PI_File_Generation_Failed_Message="//div[text()='Excel download failed due to system error. Please try again.']";
 public String PI_File_Generation_Failed_Message_description = "The pi file generation failed message for 'Excel download failed due to system error. Please try again.'";
 
 public String PI_File_Generation_Completed_Message="//div[text()='Excel file downloaded successfully']";
 public String PI_File_Generation_Completed_Message_description = "The pi file generation completed message for 'Excel file downloaded successfully'.";
 
 public String Progress_Modal_Close_Btn="//span[text()='Price Inspector Download']/preceding::i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white'][1]";
 public String Progress_Modal_Close_Btn_description = "The progress modal close btn for 'Price Inspector Download'.";

 public String DownloadExcelCloseBtn = "(//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white'])[1]";
 public String DownloadExcelCloseBtn_description = "The download excel close btn element";

 public String ExcelDownload_Success_Message = "//div[text()='Excel file downloaded successfully']";
 public String ExcelDownload_Success_Message_description = "The excel download success message for 'Excel file downloaded successfully'.";





















}
