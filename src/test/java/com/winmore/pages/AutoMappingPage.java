package com.winmore.pages;

public class AutoMappingPage {
    
public String AdjustAutoMappedScreen_Header= "//div[@class='lxtable--full__header__title']//h1[contains(text(),'Adjust Auto-Mapped')]";
public String AdjustAutoMappedScreen_Header_Description= "Header of Adjust Auto-Mapped screen";

public String AdjustAutoMappedColumnHeaderValue(int index) {
	return "(//div[@class='client-components-table-renderer-components-column-mapping-workflow-components-confirm-match-__confirm-match-module__rowContainer row'][1]//ul)[1]//li[" + index + "]//div";
}
public String AdjustAutoMappedColumnHeaderValue_Description= "Mapped Excel Column header value in Adjust Auto-Mapped screen";


}
