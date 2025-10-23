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

    public String PITable_ColumnHeader_AdhocDestHour = "//div[contains(text(),'Adhoc Dest Hour')] | //p[normalize-space()='Adhoc Dest Hour']";
    public String PITable_ColumnHeader_AdhocDestHour_Description = "Adhoc Dest Hour column header in PI table";

}
