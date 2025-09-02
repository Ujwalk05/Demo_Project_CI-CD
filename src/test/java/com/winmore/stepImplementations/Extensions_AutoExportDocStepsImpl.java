package com.winmore.stepImplementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.winmore.pageinitializer.PageInitializer;

public class Extensions_AutoExportDocStepsImpl extends PageInitializer{
	
	public static Logger log = LogManager.getLogger(LoginStepsImpl.class);

	public static void clickBrowseTab() {
		actionHelper.waitForElementToBeVisible(extensionsPage.browseButton);
		clickHelper.click(extensionsPage.browseButton);
		commonUtils.sleep(4000);
	}

}
