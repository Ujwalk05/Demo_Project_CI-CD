package com.winmore.testhelper;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testingdata.ExcelDataHandler;
import com.winmore.utils.Log;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MLCasesHelp extends PageInitializer {

    // ✅ Centralized download directory path for the entire framework
    public static final Path FRAMEWORK_DOWNLOAD_DIR = Paths.get(
        System.getProperty("user.dir"), "src", "test", "resources", "DownloadedFiles"
    );

    // ----------------------------------------------------------------
    // Excel Column Mapping Verification
    // ----------------------------------------------------------------

    /**
     * Compares a column header value shown in the app with the value in an Excel file.
     *
     * @param MappedColumnShowing locator of the column header in the app
     * @param FileName            Excel file name (with extension) inside ExcelData folder
     * @param SheetName           sheet name in the Excel file
     * @param rowNumber           row number (1-based)
     * @param columnNumber        column number (1-based)
     */
    public static void columnMappingCheckWithExcel(String MappedColumnShowing, String FileName,
            String SheetName, int rowNumber, int columnNumber) throws Throwable {

        String mappedColumnValueShowingInApp = validationHelper.getTrimmedText(MappedColumnShowing);
        String excelColumnHeader = ExcelDataHandler.readExcelDataFromFolder(
                "ExcelData", FileName, SheetName, rowNumber, columnNumber);

        Assert.assertEquals(excelColumnHeader, mappedColumnValueShowingInApp,
                "The value showing in app '" + mappedColumnValueShowingInApp
                + "' is not matching with excel column header value '" + excelColumnHeader + "'");
    }

    // ----------------------------------------------------------------
    // Download File Verification Utilities
    // ----------------------------------------------------------------

    /**
     * Polls the DownloadedFiles directory until a matching file appears or timeout is reached.
     *
     * @param expectedFilePrefix      file name prefix to match (e.g. "tl_pricing")
     * @param expectedExtension       file extension to match (e.g. ".xlsx")
     * @param timeoutInSeconds        max wait time in seconds
     * @param pollingIntervalInMillis polling interval in milliseconds
     * @return true if file found within timeout, false otherwise
     */
    public static boolean waitForDownloadedFile(String expectedFilePrefix, String expectedExtension,
            int timeoutInSeconds, int pollingIntervalInMillis) throws AutomationException {

        long timeoutAt = System.currentTimeMillis() + (timeoutInSeconds * 1000L);

        while (System.currentTimeMillis() < timeoutAt) {
            if (Files.exists(FRAMEWORK_DOWNLOAD_DIR)) {
                try (Stream<Path> files = Files.list(FRAMEWORK_DOWNLOAD_DIR)) {
                    boolean found = files.anyMatch(
                        path -> isMatchingDownloadedFile(path, expectedFilePrefix, expectedExtension));
                    if (found) return true;
                } catch (IOException e) {
                    throw new AutomationException(
                        "Unable to read download directory: " + FRAMEWORK_DOWNLOAD_DIR, e);
                }
            }
            try {
                Thread.sleep(pollingIntervalInMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AutomationException(
                    "Interrupted while waiting for downloaded file verification", e);
            }
        }
        return false;
    }

    /**
     * Checks if a given file path matches the expected prefix and extension,
     * and is not an in-progress temp file.
     */
    public static boolean isMatchingDownloadedFile(Path filePath,
            String expectedFilePrefix, String expectedExtension) {

        if (!Files.isRegularFile(filePath)) return false;

        String fileName          = filePath.getFileName().toString().toLowerCase();
        String prefixLower       = expectedFilePrefix.toLowerCase();
        String extensionLower    = expectedExtension.toLowerCase();

        return fileName.startsWith(prefixLower)
            && fileName.endsWith(extensionLower)
            && !fileName.endsWith(".crdownload")
            && !fileName.endsWith(".part")
            && !fileName.endsWith(".tmp");
    }

    /**
     * Asserts that the downloaded file exists in the DownloadedFiles folder.
     * Call this from any @Then step after a download is triggered.
     *
     * @param filePrefix  expected file name prefix
     * @param extension   expected file extension (e.g. ".xlsx")
     * @param label       human-readable label for logging/assertion messages
     */
    public static void assertFileDownloaded(String filePrefix, String extension,
            String label) throws AutomationException {

        boolean isDownloaded = waitForDownloadedFile(filePrefix, extension, 30, 1000);
        Assert.assertTrue(isDownloaded,
            "Expected downloaded file was not found. Prefix: " + filePrefix
            + ", Extension: " + extension);
        Log.info("✅ Verified that the file '" + label + "' was downloaded successfully");
    }
}