package com.winmore.pages;

public class InitialMappingPage {
    
    public String InitialReviewScreen_Header= "//div[@class='lxtable--full__header__title']//h1[contains(text(),'Initial Review')]";
    public String InitialReviewScreen_Header_Description= "Header of Initial Review screen";

   public String getReviewScreenMappedExcelColumnHeaderValue(int index) {
        return "((//ul[@class='column-list'])[1]//ul[1]//li[1]/span[2])[" + index + "]";
   
    }
    
    
    
    }
