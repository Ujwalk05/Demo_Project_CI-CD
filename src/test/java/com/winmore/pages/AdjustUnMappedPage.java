package com.winmore.pages;

public class AdjustUnMappedPage {
    
    public String AdjustUnmappedScreen_Header= "//div[@class='lxtable--full__header__title']//h1[contains(text(),'Adjust Unmapped')]"; 
    public String AdjustUnmappedScreen_Header_Description= "Header of Adjust Unmapped screen";
    
    // Dynamic method to get radio button locator by number
public String ImportAsAdHocColumn_1stTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer']/div[3])[1]//li/div[2]//input[@type='radio']";
public String ImportAsAdHocColumn_1stTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 1st table map unmatched checkbox element.";
public String ImportAsAdHocColumn_2ndTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer']/div[3])[2]//li/div[2]//input[@type='radio']";
public String ImportAsAdHocColumn_2ndTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 2nd table map unmatched checkbox element.";
public String ImportAsAdHocColumn_3rdTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer']/div[3])[3]//li/div[2]//input[@type='radio']";
public String ImportAsAdHocColumn_3rdTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 3rd table map unmatched checkbox element.";
public String ImportAsAdHocColumn_4thTable_MapUnmatched_Checkbox=  "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer']/div[3])[4]//li/div[2]//input[@type='radio']";
public String ImportAsAdHocColumn_4thTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 4th table map unmatched checkbox element.";
 public String ImportAsAdHocColumn_17thTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer'])[17]//li/div[3]//input[@type='radio']";
public String ImportAsAdHocColumn_17thTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 17th table map unmatched checkbox element.";
public String ImportAsAdHocColumn_16thTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer'])[16]//li/div[3]//input[@type='radio']";
public String ImportAsAdHocColumn_16thTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 16th table map unmatched checkbox element.";
public String ImportAsAdHocColumn_18thTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer']/div[3])[18]//li/div[2]//input[@type='radio']";
public String ImportAsAdHocColumn_18thTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 18th table map unmatched checkbox element.";
public String ImportAsAdHocColumn_19thTable_MapUnmatched_Checkbox = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer']/div[3])[19]//li/div[2]//input[@type='radio']";
public String ImportAsAdHocColumn_19thTable_MapUnmatched_Checkbox_description = "The import as ad hoc column 19th table map unmatched checkbox element.";

    public String Select_Segment_Dropdown_In_Mapping_Screen= "(//span[contains(text(),'Select Segment')])[%s]/following::button[contains(@class,'segmentSelectionButton')][1]";
    public String Select_Segment_Dropdown_In_Mapping_Screen_Description= "Select Segment dropdown in Mapping screen";

   public String Select_LeaveColumnUnmappedIgnoreColumn_MappingScreen = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__radio']//span[.=' Leave Column Unmapped/Ignore Column'])[%s]";
    public String Select_LeaveColumnUnmappedIgnoreColumn_MappingScreen_Description = "Leave Column Unmapped/Ignore Column radio button in Mapping screen";

   public String Select_Specific_Segment_From_Dropdown= "//div[@class='-u-dropdown__wrapper']//span[normalize-space()='%s']";
    public String Select_Specific_Segment_From_Dropdown_Description= "Specific Segment option in Select Segment dropdown";

public String AdhocType_Other_Generic_Xpath_In_Mapping_Screen= "(//input[@type='radio' and @value='OTHER'])[%s]";
public String AdhocType_Other_Generic_Xpath_In_Mapping_Screen_Description= "Adhoc Type Other radio button in Mapping screen";

public String MapColumn_2ndTable_MapUnmatched = "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-column-mapping-table-__index-module__container'])[2]//li[2]//input";
public String MapColumn_2ndTable_MapUnmatched_Description = "The map column in 2nd table for map unmatched section.";





}
