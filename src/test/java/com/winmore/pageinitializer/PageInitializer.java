package com.winmore.pageinitializer;

import com.winmore.actions.ClickActionHelper;
import com.winmore.actions.InputActionHelper;
import com.winmore.actions.UtilityActionHelper;
import com.winmore.actions.ValidationActionHelper;
import com.winmore.pages.ExtensionsPage;
import com.winmore.pages.HomePage;
import com.winmore.pages.LoginPage;
import com.winmore.testingdata.PropertyDataHandler;
import com.winmore.utils.CommonUtils;
import com.winmore.utils.TestEnvironmentData;

public class PageInitializer {
	
	// Helpers
    public static UtilityActionHelper actionHelper;
    public static ValidationActionHelper validationHelper;
    public static InputActionHelper inputHelper;
    public static ClickActionHelper clickHelper;

    // Pages
    public static LoginPage loginPage;
    protected static PropertyDataHandler propertyData;
    protected TestEnvironmentData envData;
    public static HomePage homePage;
    public static ExtensionsPage extensionsPage;
    
    // CommonUtils
    public static CommonUtils commonUtils;

    public PageInitializer() {
        // initialize helpers once
        actionHelper = new UtilityActionHelper();
        validationHelper = new ValidationActionHelper();
        inputHelper =  new InputActionHelper();
        clickHelper = new ClickActionHelper();
        propertyData = new PropertyDataHandler();
        envData = new TestEnvironmentData();
        loginPage = new LoginPage();
        commonUtils = new CommonUtils();
        homePage = new HomePage();
        extensionsPage = new ExtensionsPage();
    }

}
