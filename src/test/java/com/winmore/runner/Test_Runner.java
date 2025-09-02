package com.winmore.runner;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.winmore.base.TestBase;
import com.winmore.testactions.EmailReport;
import com.winmore.utils.PlaywrightDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = { "com.winmore.stepdefinitions",
        "com.winmore.hooks" }, plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber-json-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }, monochrome = true)
public class Test_Runner extends AbstractTestNGCucumberTests {
	
//	@BeforeTest
//    public void setEnvironmentForRunner() {
//        // This is a fallback and can be commented out if you only use Maven/TestNG
//        if (System.getProperty("environment") == null) {
//            System.setProperty("environment", "qaprod1");
//        }
//    }
	
//	@BeforeSuite(alwaysRun = true)
//    @Parameters("environment")
//    public void setEnvFromXml(String environment) {
//        System.setProperty("environment", environment); 
//        System.out.println("🔧 Environment set from TestNG XML: " + environment);
//    }
	
//	@BeforeSuite(alwaysRun = true)
//    @Parameters("environment")
//    public void setEnvFromXml(@Optional("qaprod") String environment) {
//        //System.setProperty("environment", environment);
//        System.out.println("🔧 Environment set from TestNG: " + environment);
//        
//        TestBase.environment = System.getProperty("environment", environment);
//        TestBase.maillist = System.getProperty("mailRecipients", ""); // << important line
//        System.out.println("Mail Recipients: " + TestBase.maillist);
//    }
	
	//@BeforeSuite(alwaysRun = true)
	@BeforeClass(alwaysRun = true)
	@Parameters({"environment","mailRecipients"})
	public void setEnvFromXml(@Optional("napreprod") String environment,
	                          @Optional("amit.kumar@winmore.app") String mailRecipients) {
		
		// 🔧 Always sync environment in both System and TestBase
	    System.setProperty("environment", environment);
	    TestBase.environment = environment;
		TestBase.maillist = mailRecipients;
		
		String ExeSuite = System.getProperty("cucumber.filter.tags");
		if (ExeSuite != null) {
		    String executionsuite = ExeSuite.replace("@", "").trim();
		    TestBase.executionsuite = executionsuite;
		    System.out.println("The Executing tag is >>>> " + executionsuite);
		} else {
			// ✅ Default Tag (set here if nothing is passed from runner/command line)
	        String defaultTag = "@Login";   // <-- change this as per your need
	        TestBase.executionsuite = defaultTag.replace("@", "");
	        System.setProperty("cucumber.filter.tags", defaultTag); 
	        System.out.println("⚡ No tags specified. Using default tag: " + defaultTag);
		}
		
	    TestBase.environment = environment;
	    TestBase.maillist = mailRecipients;
	    System.out.println("Mail Recipients (from TestNG): " + TestBase.maillist);
	    
	 // ✅ Log final values to confirm
	    //System.out.println("🔧 Final Environment: " + TestEnvironmentData.getEnv());
	    System.out.println(environment);
	}
	
//	 @AfterAll
//	    public static void afterAllTests() {
//	        try {
//	        	Thread.sleep(3000);
//	        	// Debug first
//	            debugReportStructure();
//	        	EmailReport.sendMailReport();
//	            PlaywrightDriver.closeBrowser();
//	            PlaywrightDriver.quitPlaywright();
//	            System.out.println("❌ Browser closed successfully.");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
	
	
  // Use @AfterAll to close the browser once after all tests are done
  //@AfterAll
  @AfterSuite
  public void afterAllTests() {
      try {
          
          
          PlaywrightDriver.closeBrowser();
          PlaywrightDriver.quitPlaywright();
          System.out.println("❌ Browser closed successfully.");
          
       // Flush Extent explicitly if you're using it
//          if (ExtentManager.getExtent() != null) {
//              ExtentManager.getExtent().flush();
//          }

          // Now wait briefly for async PDF flush
          System.out.println("Waiting for reports to be generated...");
          Thread.sleep(5000);

          //debugReportStructure();
          // Finally send email
          EmailReport.sendMailReport();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
	 
	 
	 private void debugReportStructure() {
		    System.out.println("=== DEBUG: Checking Reports Directory ===");
		    File reportsDir = new File("Reports");
		    
		    if (reportsDir.exists()) {
		        System.out.println("Reports directory exists at: " + reportsDir.getAbsolutePath());
		        File[] reportDirs = reportsDir.listFiles(File::isDirectory);
		        
		        if (reportDirs != null && reportDirs.length > 0) {
		            System.out.println("Found " + reportDirs.length + " report directories:");
		            
		            // Sort by latest modified
		            Arrays.sort(reportDirs, Comparator.comparingLong(File::lastModified).reversed());
		            
		            for (int i = 0; i < reportDirs.length; i++) {
		                File dir = reportDirs[i];
		                System.out.println((i + 1) + ". " + dir.getName() + 
		                                 " (Last Modified: " + new Date(dir.lastModified()) + ")");
		                
		                // Check for PDF and HTML reports in this directory
		                File pdfReport = new File(dir, "PDFReport/ExtentPdf.pdf");
		                File htmlReport = new File(dir, "Reports/Spark.html");
		                
		                System.out.println("   PDF Report exists: " + pdfReport.exists());
		                System.out.println("   HTML Report exists: " + htmlReport.exists());
		                
		                if (pdfReport.exists()) {
		                    System.out.println("   PDF Path: " + pdfReport.getAbsolutePath());
		                }
		                if (htmlReport.exists()) {
		                    System.out.println("   HTML Path: " + htmlReport.getAbsolutePath());
		                }
		            }
		        } else {
		            System.out.println("No report directories found in Reports folder");
		        }
		    } else {
		        System.out.println("Reports directory does not exist");
		    }
		    System.out.println("=== END DEBUG ===");
		}

}
