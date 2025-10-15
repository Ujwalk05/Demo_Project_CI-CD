package com.winmore.pageinitializer;

import com.winmore.actions.ClickActionHelper;
import com.winmore.actions.FileUploadHelper;
import com.winmore.actions.InputActionHelper;
import com.winmore.actions.UtilityActionHelper;
import com.winmore.actions.ValidationActionHelper;
import com.winmore.base.TestBase;
import com.winmore.pages.AdjustUnMappedPage;
import com.winmore.pages.AutoMappingPage;
import com.winmore.pages.ExtensionsPage;
import com.winmore.pages.HomePage;
import com.winmore.pages.InitialMappingPage;
import com.winmore.pages.LoginPage;
import com.winmore.pages.ShipperFileImportpage;
import com.winmore.pages.QuickCreatePage;
import com.winmore.pages.WorkFlowPage;
import com.winmore.testhelper.MLCasesHelp;
import com.winmore.testingdata.PropertyDataHandler;
import com.winmore.utils.CommonUtils;
import com.winmore.utils.TestEnvironmentData;

public class PageInitializer {

	// Helpers
	public static ClickActionHelper clickHelper;
	public static FileUploadHelper uploadHelper;
	public static InputActionHelper inputHelper;
	public static UtilityActionHelper actionHelper;
	public static ValidationActionHelper validationHelper;
	public static MLCasesHelp mlCasesHelp;


	//Data related
	protected static PropertyDataHandler propertyData;
	protected TestEnvironmentData envData;

    
    // Pages
    public static LoginPage loginPage;

    public static HomePage homePage;
    public static ShipperFileImportpage Shipperimportpage;
    public static ExtensionsPage extensionsPage;
	public static QuickCreatePage quickCreatePage;
	public static WorkFlowPage workFlowPage;
	public static InitialMappingPage initialMappingPage;
	public static AutoMappingPage autoMappingPage;
	public static AdjustUnMappedPage adjustUnMappedPage;
    
    // CommonUtils
    public static CommonUtils commonUtils;

	//Constants
	public static TestBase testBase;



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
		uploadHelper = new FileUploadHelper();
		Shipperimportpage = new ShipperFileImportpage();
		quickCreatePage = new QuickCreatePage();
		testBase = new TestBase();
		workFlowPage = new WorkFlowPage();
		initialMappingPage = new InitialMappingPage();
	 	//mlCasesHelp = new MLCasesHelp();
	    autoMappingPage = new AutoMappingPage();
	    adjustUnMappedPage = new AdjustUnMappedPage();

	}

}
