package com.winmore.stepdefinitions.MappingProcess;

import com.winmore.pageinitializer.PageInitializer;
import com.winmore.utils.Log;

import io.cucumber.java.en.*;

public class MappingReview extends PageInitializer{

    @Given("the user verifying the presence of Mapping Review screen")
    public void userVerifyingPresenceOfMappingReviewScreen() {
        actionHelper.waitForElementToBeVisible(mappingReviewPage.MappingReviewScreen_Header);
        Log.info("✅ User is on the Mapping Review screen");
    }

    @Given("the user selecting the Mapping Screen Proceed button")
    public void userSelectingMappingScreenProceedButton() {
        // Click Proceed
        clickHelper.click(mappingReviewPage.MappingScreenProceed_Btn);
        Log.info("✅ User clicked on the Mapping Screen Proceed button");

        // 1) If a progress modal appears, wait for completion and close when available
        try {
            boolean modalVisible = validationHelper.isElementDisplayedShortTimeCheck(
                    mappingReviewPage.ShipperFileImport_Status_ProgressModal);

            if (modalVisible) {
                int attempts = 0;
                while (attempts < 7) {
                    boolean closeVisible = validationHelper.isElementDisplayedShortTimeCheck(
                            mappingReviewPage.ShipperFileImport_StatusModalorPopUp_closeBtn);
                    if (closeVisible) {
                        Log.info("⏹ File processing completed; closing progress modal");
                        clickHelper.click(mappingReviewPage.ShipperFileImport_StatusModalorPopUp_closeBtn);
                        break;
                    } else {
                        attempts++;
                        Log.info("⌛ File import processing taking time (attempt " + attempts + ")");
                        commonUtils.sleep(3000);
                    }
                }
            } else {
                Log.info("ℹ️ Progress modal not shown after Proceed click");
            }
        } catch (Exception ignore) {
            // Keep flow resilient; continue to record screen waits
        }

        // 2) Wait for record page loader to disappear and for Expand & Edit button to be ready
        int retry = 0;
        while (retry < 4) {
            // If loader is visible, wait a bit more
            try {
                boolean recordLoader = validationHelper.isElementDisplayedShortTimeCheck(
                        mappingReviewPage.Progress_Loader_In_Record_Screen);
                if (recordLoader) {
                    commonUtils.sleep(3000);
                }
            } catch (Exception ignored) {
                // ignore visibility probe failures
            }

            // Now try to wait for the Expand & Edit button
            // try {
            //     actionHelper.waitForElementToBeVisible(mappingReviewPage.ExpandAndEditBtn_FileUploadHistoryTable);
            //     boolean expandReady = validationHelper.isElementDisplayedShortTimeCheck(
            //             mappingReviewPage.ExpandAndEditBtn_FileUploadHistoryTable);
            //     if (expandReady) {
            //         Log.info("✅ Shipper file status preview screen is loaded");
            //         break;
            //     }
            // } catch (Exception e) {
            //     // fall through to retry
            // }

            retry++;
            Log.info("⌛ Shipper file status preview still loading; retry " + retry);
            commonUtils.sleep(3000);
        }
    }
}
