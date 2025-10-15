package com.winmore.pages;

public class AdjustUnMappedPage {
    
    public String AdjustUnmappedScreen_Header= "//div[@class='lxtable--full__header__title']//h1[contains(text(),'Adjust Unmapped')]"; 
    public String AdjustUnmappedScreen_Header_Description= "Header of Adjust Unmapped screen";
    
    // Dynamic method to get radio button locator by number
    public String getImportAsAdhocColumnRadio(String radioButtonNumber) {
        return "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-mapping-summary-__index-module__mappingSummaryContainer'])[" + radioButtonNumber + "]//li/div[3]//input[@type='radio']";
    }
    public String ImportAsAdhocColumnRadio_Description= "Import as AdHoc column radio button dynamically";

}
