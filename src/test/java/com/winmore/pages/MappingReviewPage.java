package com.winmore.pages;

public class MappingReviewPage {
    public String MappingReviewScreen_Header = "//div[@class='lxtable--full__header__title']//h1[contains(text(),'Mapping Review')]";
    public String MappingReviewScreen_Header_Description = "Header of the Mapping Review screen";

    public String MappingScreenProceed_Btn = "//div[@class='mapping-header-buttons']//span[contains(text(),'Proceed')]";
    public String MappingScreenProceed_Btn_Description = "Mapping Screen Proceed button";

    public String ShipperFileImport_Status_ProgressModal = "//div[@class='client-components-generic-progress-notification-modal-__index-module__modal']";
    public String ShipperFileImport_Status_ProgressModal_Description = "Shipper File Import Status Progress Modal";

    public String ShipperFileImport_StatusModalorPopUp_closeBtn = "//div[@class='client-components-generic-progress-notification-modal-__index-module__modal']//i[@class='-u-icon -u-icon--x client-components-modal-close-__index-module__closeIcon -u-icon--white']";
    public String ShipperFileImport_StatusModalorPopUp_closeBtn_Description = "Shipper File Import Status Modal or PopUp Close button";

    public String Progress_Loader_In_Record_Screen="//div[@class='ball-scale-multiple client-components-loading-__pulse-module__pulse client-components-loading-__pulse-module__default client-components-__loader-module__loader']";
    public String Progress_Loader_In_Record_Screen_Description="Progress Loader In Record Screen";

    public String ExpandAndEditBtn_FileUploadHistoryTable = "//button[@class='-u-btn -u-btn--pill']//span[contains(text(),'Expand and Edit')])[1]";
    public String ExpandAndEditBtn_FileUploadHistoryTable_Description = "Expand and Edit button in File Upload History table";

}
