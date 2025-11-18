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

   public String PriceEditModal_CloseBtn = "//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white']";
   public String PriceEditModal_CloseBtn_description = "The price edit modal close btn element.";

  public String PITable_CloseBtn = "//button[@class='-u-btn -u-btn--pill -u-btn--block']//span[contains(text(),'Close')]";
  public String PITable_CloseBtn_description = "The pitable close btn for 'Close'.";


}
