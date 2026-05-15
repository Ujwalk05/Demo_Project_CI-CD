package com.winmore.ExcelUtility;

import com.winmore.exceptions.AutomationException;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testhelper.MLCasesHelp;
import com.winmore.utils.Log;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class ExcelReader extends PageInitializer{

    /**
     * Finds the first file in DownloadedFiles folder matching the given prefix and .xlsx extension,
     * then reads the cell value at the given row and column (both 0-based index).
     *
     * @param filePrefix  file name prefix to match (e.g. "tl_pricing")
     * @param sheetIndex  sheet index (0 = first sheet)
     * @param rowIndex    row index (0-based)
     * @param colIndex    column index (0-based)
     * @return cell value as String
     */
    public static String readCellFromDownloadedFile(String filePrefix, int sheetIndex,
            int rowIndex, int colIndex) throws AutomationException {

        Path downloadDir = MLCasesHelp.FRAMEWORK_DOWNLOAD_DIR;

        // Find the matching downloaded file
        Path matchedFile = findDownloadedFile(downloadDir, filePrefix, ".xlsx");

        // Read the cell
        try (FileInputStream fis = new FileInputStream(matchedFile.toFile());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                throw new AutomationException(
                    "Sheet at index " + sheetIndex + " not found in file: " + matchedFile.getFileName());
            }

            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                throw new AutomationException(
                    "Row " + rowIndex + " not found in sheet at index " + sheetIndex);
            }

            Cell cell = row.getCell(colIndex);
            if (cell == null) {
                throw new AutomationException(
                    "Cell at row " + rowIndex + ", col " + colIndex + " is empty or null");
            }

            String value = getCellValueAsString(cell);
            Log.info("📄 Read cell [row=" + rowIndex + ", col=" + colIndex + "] = '" + value
                    + "' from file: " + matchedFile.getFileName());
            return value;

        } catch (IOException e) {
            throw new AutomationException(
                "Failed to read Excel file: " + matchedFile.getFileName(), e);
        }
    }

    /**
     * Finds first file in the given directory matching prefix + extension.
     */
    private static Path findDownloadedFile(Path directory, String filePrefix,
            String extension) throws AutomationException {

        if (!Files.exists(directory)) {
            throw new AutomationException(
                "DownloadedFiles directory does not exist: " + directory);
        }

        try (Stream<Path> files = Files.list(directory)) {
            return files
                .filter(Files::isRegularFile)
                .filter(p -> {
                    String name = p.getFileName().toString().toLowerCase();
                    return name.startsWith(filePrefix.toLowerCase())
                        && name.endsWith(extension.toLowerCase())
                        && !name.endsWith(".crdownload")
                        && !name.endsWith(".part")
                        && !name.endsWith(".tmp");
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "No downloaded file found with prefix '" + filePrefix
                    + "' and extension '" + extension + "' in: " + directory));
        } catch (IOException e) {
            throw new AutomationException(
                "Failed to scan DownloadedFiles directory: " + directory, e);
        }
    }

    /**
     * Converts any cell type to a clean String value.
     */
    private static String getCellValueAsString(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }




    /*
    * Author= @Ujwal Kumar
    * Usage= will get the latest file from the downloaded directory
    
    */
    public static Path getLatestFileFromDir(Path downloadDir) throws AutomationException {
        if (!Files.exists(downloadDir)) {
            throw new AutomationException("Directory does not exist: " + downloadDir);
        }

        try (Stream<Path> files = Files.list(downloadDir)) {
            Path latestFile = files
                .filter(Files::isRegularFile)
                .filter(path -> {
                    String fileName = path.getFileName().toString().toLowerCase();
                    return !fileName.endsWith(".crdownload")
                        && !fileName.endsWith(".part")
                        && !fileName.endsWith(".tmp");
                })
                .max(Comparator.comparingLong(path -> path.toFile().lastModified()))
                .orElse(null);

            if (latestFile == null) {
                throw new AutomationException("No files found in directory: " + downloadDir);
            }

            return latestFile;
        } catch (IOException e) {
            throw new AutomationException("Failed to read directory: " + downloadDir, e);
        }
    }

    public static File getLatestFilefromDir(String downloadDir) throws AutomationException {
        Path latestFile = getLatestFileFromDir(Paths.get(downloadDir));
        return latestFile.toFile();
    }

    public static Path deleteLatestFileFromDir(Path downloadDir) throws AutomationException {
        Path latestFile = getLatestFileFromDir(downloadDir);

        try {
            Files.delete(latestFile);
            return latestFile;
        } catch (IOException e) {
            throw new AutomationException("Failed to delete file: " + latestFile, e);
        }
    }





}