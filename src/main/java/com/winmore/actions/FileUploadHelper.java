package com.winmore.actions;

import java.nio.file.Path;
import java.nio.file.Paths;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.winmore.exceptions.AutomationException;
import com.winmore.utils.PlaywrightDriver;

public class FileUploadHelper {
	
	public static Page page;

	public FileUploadHelper() {

		page = PlaywrightDriver.getPage();

	}
	
	
	public boolean uploadFile(String locatorKey, String relativeFilePath) throws AutomationException {
	    boolean uploaded = false;
	    try {
	        // Build full file path
	        String filePath = System.getProperty("user.dir") + relativeFilePath;

	        // Perform upload
	        page.locator(locatorKey).setInputFiles(Paths.get(filePath));

	        uploaded = true;
	        System.out.println("File uploaded successfully: " + filePath);
	    } catch (Exception e) {
	        throw new AutomationException("File upload failed for: " + relativeFilePath + " | Error: " + e.getMessage());
	    }
	    return uploaded;
	}
	
	public void uploadAndSetFile(String fileName, String locator) {
        try {
            Path filePath = Paths.get(System.getProperty("user.dir"), "/src/test/resources/ExcelData/", fileName);

            FileChooser fileChooser = page.waitForFileChooser(() -> {
                page.locator(locator).click();
            });

            fileChooser.setFiles(filePath);

            page.waitForLoadState();
            System.out.println("✅ File uploaded successfully: " + fileName);

        } catch (Exception e) {
            throw new RuntimeException("❌ File upload failed for: " + fileName, e);
        }
    }


}
