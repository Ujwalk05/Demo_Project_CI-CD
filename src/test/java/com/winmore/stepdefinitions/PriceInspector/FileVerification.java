package com.winmore.stepdefinitions.PriceInspector;

import com.winmore.ExcelUtility.ExcelReader;
import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testhelper.MLCasesHelp;
import com.winmore.utils.Log;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.nio.file.Path;

public class FileVerification extends PageInitializer{

    /**
     * Author: Ujwal
     * usage: this is to verify the excel values in the downloaded PI table File by providing row and column index
     */
    @When("the user verifying that the {string} column present in the file {string}")
    public void ExcelColumn_verification(String ColumnName, String FileName) throws AutomationException {

        String actualColumnValue;
        String expectedColumnValue;

        switch (FileName) {

            case "tl_pricing_bid_set":

                switch (ColumnName) {

                    case "Origin Hours":
                        actualColumnValue = ExcelReader.readCellFromDownloadedFile("tl_pricing", 0, 0, 5);                        expectedColumnValue = ColumnName;
                        break;

                    case "adhoc dest hour":    
                    actualColumnValue = ExcelReader.readCellFromDownloadedFile("tl_pricing", 0, 0, 6);                        expectedColumnValue = ColumnName;
                    break;
                        

                    default:
                        throw new RuntimeException(
                            "No column verification mapping defined for column: " + ColumnName
                            + " in file: " + FileName);
                }
                Assert.assertEquals(actualColumnValue, expectedColumnValue,
                    "Column header mismatch! Expected: '" + expectedColumnValue
                    + "' but found: '" + actualColumnValue + "' in file: " + FileName);
                Log.info("✅ Column '" + ColumnName + "' verified successfully in file '" + FileName + "'");
                break;

            default:
                throw new RuntimeException(
                    "No file mapping defined for: " + FileName);
        }
    }

    @Then("the user deleting {string} file from directory")
    public void delete_files(String fileName) throws AutomationException {
        Path deletedFile = ExcelReader.deleteLatestFileFromDir(MLCasesHelp.FRAMEWORK_DOWNLOAD_DIR);
        Log.info("✅ Deleted latest file from directory for '" + fileName + "': " + deletedFile.getFileName());
    }
}