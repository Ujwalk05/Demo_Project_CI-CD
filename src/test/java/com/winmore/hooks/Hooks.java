package com.winmore.hooks;



import java.nio.file.Paths;

import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testactions.TestSummary;
import com.winmore.utils.Log;
import com.microsoft.playwright.Page;
import io.cucumber.java.*;
import com.winmore.utils.PlaywrightDriver;
//import com.winmore.WebProject.services.TestReportClient;

public class Hooks {
//	public Page page;
//    //private static TestReportClient reportClient;
//    private long startTime;
//
//    @BeforeAll
//    public static void beforeAllTests() {
//        try {
//        	
//        	// Get from TestNG param or CLI (-Denvironment=staging), default to qaenv
////            String environment1 = PropertyDataHandler.fetchPropertyValue("qaprod", "baseurl");
////            System.setProperty("environment", environment1);
////
////            System.out.println("Running tests on environment: " + environment1);
//            
//            
//            String apiUrl = System.getenv().getOrDefault("TESTREPORT_API_URL", "http://localhost:3001/api");
//            String environment = System.getenv().getOrDefault("TESTREPORT_ENVIRONMENT", "qaProd");
//
//            //reportClient = new TestReportClient(apiUrl);
//            //reportClient.startExecution("Winmore Tests Suite", environment);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Before
//    public void setUp(Scenario scenario) {
//        PlaywrightDriver.setupDriver();
//        startTime = System.currentTimeMillis();
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        long duration = System.currentTimeMillis() - startTime;
//        String status = scenario.isFailed() ? "failed" : "passed";
//        String errorMessage = scenario.isFailed()
//                ? (scenario.getStatus() != null ? scenario.getStatus().toString() : "Unknown error")
//                : null;
//
//        try {
//            // Upload test result
//            //reportClient.addTestResult(scenario.getName(), status, duration, errorMessage);
//
//            // Upload screenshot if failed
//            if (scenario.isFailed()) {
//                Page page = PlaywrightDriver.getPage();
//                String timestamp = java.time.LocalDateTime.now()
//                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
//                String screenshotName = "screenshot_" + timestamp + ".png";
//                Path screenshotPath = Paths.get("./screenshot/" + screenshotName);
//
//                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
//                scenario.attach(screenshot, "image/png", screenshotName);
//
//                //reportClient.uploadArtifact(screenshotPath.toFile(), scenario.getName(), "screenshot");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        PlaywrightDriver.closeBrowser();
//        PlaywrightDriver.quitPlaywright();
//    }
//
//    @AfterAll
//    public static void afterAllTests() {
//        try {
////            if (reportClient != null) {
////                reportClient.completeExecution();
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @BeforeStep
//    public void beforeEachStep(Scenario scenario) {
//        startTime = System.currentTimeMillis();
//    }
//
//    @AfterStep
//    public void afterEachStep(Scenario scenario) {
//        // try {
//        // // long stepDuration = System.currentTimeMillis() - startTime;
//        // // String stepName = scenario.getId(); // Gets the current step text
//        // // String stepStatus = scenario.isFailed() ? "failed" : "passed";
//
//        // // // Take screenshot after each step
//        // // Page page = PlaywrightDriver.getPage();
//        // // String timestamp = java.time.LocalDateTime.now()
//        // // .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
//        // // String screenshotName = "step_" + timestamp + ".png";
//        // // Path screenshotPath = Paths.get("./screenshot/" + screenshotName);
//
//        // // byte[] screenshot = page.screenshot(new
//        // // Page.ScreenshotOptions().setPath(screenshotPath));
//        // // scenario.attach(screenshot, "image/png", screenshotName);
//
//        // // // Upload step result and screenshot
//        // // // reportClient.addTestResult(stepName, stepStatus, stepDuration, null);
//        // // // reportClient.uploadArtifact(screenshotPath.toFile(), stepName,
//        // // // "step-screenshot");
//        // } catch (Exception e) {
//        // e.printStackTrace();
//        // }
//    }
//    
////    @BeforeSuite(alwaysRun = true)
////    @Parameters("environment")
////    public void setEnvFromXml(String environment) {
////        System.setProperty("environment", environment); 
////        System.out.println("🔧 Environment set from TestNG XML: " + environment);
////    }
///
///
///
///
///
///

	static int cnt =0;
	
	// @BeforeAll
    // public static void beforeAllTests() {
    //     try {
    //         PlaywrightDriver.setupDriver();
    //         System.out.println("✅ Browser launched successfully.");
    //         Log.info("✅ Browser launched successfully.");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }


	@BeforeAll
public static void beforeAllTests() {
    try {
        PlaywrightDriver.setupDriver();
        // Initialize all static PageInitializer fields once here
        new PageInitializer();
        System.out.println("✅ Browser launched successfully.");
        Log.info("✅ Browser launched successfully.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    // This hook is for scenario-level actions, not for browser setup/teardown
    @Before
    public void setUp(Scenario scenario) {
    	Log.info("🚀 Starting Scenario Execution...");
    	TestSummary.incrementTotalTests();
        // Your existing code is fine, but we'll move browser setup out
        // PlaywrightDriver.setupDriver(); // This is now in @BeforeAll
        // Your other setup logic can go here if needed per scenario
    }

    // This hook is for scenario-level teardown
    @After
    public void tearDown(Scenario scenario) {
        // This is where you can add scenario-specific actions like screenshots
//        if (scenario.isFailed()) {
//            try {
//                Page page = PlaywrightDriver.getPage();
//                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions());
//                scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    	try {
    		// Upload test result
    		//reportClient.addTestResult(scenario.getName(), status, duration, errorMessage);

    		Log.info("🏁 Scenario Execution Finished.");
    		if (scenario.isFailed()) {
                TestSummary.incrementFailedTests();
            } else {
                TestSummary.incrementPassedTests();
            }
    		// Upload screenshot if failed
    		if (scenario.isFailed()) {
    			Page page = PlaywrightDriver.getPage();
    			//    		   String timestamp = java.time.LocalDateTime.now()
    			//    				   .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    			//    		   String screenshotName = "screenshot_" + timestamp + ".png";
    			//    		   Path screenshotPath = Paths.get("./screenshot/" + screenshotName);

    			String screenshotPath = "target/screenshots/" 
    					+ scenario.getName().replaceAll(" ", "_") 
    					+ ".png";
    			try {
    				// Save screenshot as file (needed for PDF report)
    				page.screenshot(new Page.ScreenshotOptions()
    						.setPath(Paths.get(screenshotPath))
    						.setFullPage(true));

    				// Attach screenshot to Cucumber (for HTML report)
    				byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions()
    						.setFullPage(true));
    				scenario.attach(screenshotBytes, "image/png", scenario.getName());

    			} catch (Exception e) {
    				e.printStackTrace();
    			}

    			//byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
    			//scenario.attach(screenshot, "image/png", screenshotName);

    			//reportClient.uploadArtifact(screenshotPath.toFile(), scenario.getName(), "screenshot");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    // Use @AfterAll to close the browser once after all tests are done
    // @AfterAll
//    @AfterSuite
//    public static void afterAllTests() {
//        try {
//            
//            
//            PlaywrightDriver.closeBrowser();
//            PlaywrightDriver.quitPlaywright();
//            System.out.println("❌ Browser closed successfully.");
//            
//         // Flush Extent explicitly if you're using it
////            if (ExtentManager.getExtent() != null) {
////                ExtentManager.getExtent().flush();
////            }
//
//            System.out.println("Waiting 10 seconds for reports to be generated and flushed...");
//            // Now send email
//            EmailReport.sendMailReport();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//    @AfterSuite(alwaysRun = true)
//    public void flushExtentReport() {
//        ExtentReports extent = ExtentManager.getInstance();
//        if (extent != null) {
//            extent.flush(); // ✅ forces all data to be written to Spark.html and ExtentReport.pdf
//            System.out.println("Extent reports flushed to disk.");
//        }
//    }
    

}