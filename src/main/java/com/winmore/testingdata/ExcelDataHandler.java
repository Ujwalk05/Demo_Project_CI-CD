package com.winmore.testingdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.winmore.exceptions.AutomationException;

/**
 * Utility class for handling Excel file operations.
 * This class provides methods to read, write, and manipulate data in XLSX files
 * using the Apache POI library.
 */
public class ExcelDataHandler {

	/**
	 * Reads a cell's string value from an Excel file located in the default "ExcelData" folder.
	 *
	 * @param fileName     The name of the Excel file (e.g., "data.xlsx").
	 * @param sheetName    The name of the sheet to read from.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @return The string value of the specified cell.
	 * @throws IOException         if the file cannot be read.
	 * @throws AutomationException if an error occurs during the Excel operation.
	 */
	public static String readExcelData(String fileName, String sheetName, int rowNumber, int columnNumber)
			throws IOException, AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "ExcelData", fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found in the workbook.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				System.out.println("No data present in the row " + rowNumber + ".");
				return "";
			}

			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				System.out.println("No data present in the cell at coordinates (" + rowNumber + ", " + columnNumber + ").");
				return "";
			}

			DataFormatter formatter = new DataFormatter();
			String cellValue = formatter.formatCellValue(cellDetails);
			System.out.println("Data in the required cell is: " + cellValue);

			return cellValue;
		} catch (Exception e) {
			throw new AutomationException("Error while reading the Excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Fetches all dropdown options from an Excel data validation list for a specific cell.
	 * It also asserts that the selected value from the dropdown matches a provided value.
	 *
	 * @param folderName             The name of the folder where the Excel file is located.
	 * @param selectedCellValueFromPI The value to compare with the selected dropdown cell value.
	 * @param fileName               The name of the Excel file.
	 * @param sheetName              The name of the sheet containing the dropdown.
	 * @param rowNumber              The 0-based row index of the cell with the dropdown.
	 * @param columnNumber           The 0-based column index of the cell with the dropdown.
	 * @return A List of all possible dropdown options as Strings.
	 * @throws AutomationException if the sheet, row, or cell is not found, or if an I/O error occurs.
	 */
	public static List<String> dropDownOptions(String folderName, String selectedCellValueFromPI, String fileName, String sheetName, int rowNumber, int columnNumber)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream fis = new FileInputStream(file);
			 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet mainSheet = workbook.getSheet(sheetName);
			if (mainSheet == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row rowDetails = mainSheet.getRow(rowNumber);
			if (rowDetails == null) {
				throw new AutomationException("Row " + rowNumber + " not found in sheet " + sheetName);
			}

			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				throw new AutomationException("Cell not found at row " + rowNumber + " and column " + columnNumber);
			}

			String selectedValue = cellDetails.getStringCellValue();
			assert selectedValue.equals(selectedCellValueFromPI) : "Values are not equal. Expected: " + selectedCellValueFromPI + ", Found: " + selectedValue;

			List<String> dropdownOptions = new ArrayList<>();
			for (DataValidation validation : mainSheet.getDataValidations()) {
				for (CellRangeAddress range : validation.getRegions().getCellRangeAddresses()) {
					if (range.isInRange(rowNumber, columnNumber)) {
						if (validation.getValidationConstraint().getValidationType() == DataValidationConstraint.ValidationType.LIST) {
							String formula = validation.getValidationConstraint().getFormula1();
							if (formula != null) {
								String[] formulaParts = formula.split("!");
								String referenceSheetName = formulaParts[0].replace("'", "").trim();
								String cellRange = formulaParts[1].replace("$", "");
								XSSFSheet referenceSheet = workbook.getSheet(referenceSheetName);
								if (referenceSheet == null) {
									throw new AutomationException("Referenced sheet '" + referenceSheetName + "' not found.");
								}
								CellRangeAddress refRange = CellRangeAddress.valueOf(cellRange);
								for (int r = refRange.getFirstRow(); r <= refRange.getLastRow(); r++) {
									Row refRow = referenceSheet.getRow(r);
									if (refRow != null) {
										Cell refCell = refRow.getCell(refRange.getFirstColumn());
										if (refCell != null) {
											dropdownOptions.add(refCell.getStringCellValue());
										}
									}
								}
							}
						}
					}
				}
			}
			return dropdownOptions;
		} catch (IOException e) {
			throw new AutomationException("Error while reading the Excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Writes a string value to a specified cell in an Excel file.
	 * If the row does not exist, it will be created.
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param sheetName    The name of the sheet to write to.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @param value        The string value to be written.
	 * @throws AutomationException if an error occurs during the write operation.
	 */
	public static void writeExcelData(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber, String value)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found in the workbook.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				rowDetails = sheetDetails.createRow(rowNumber);
			}

			Cell cellDetails = rowDetails.createCell(columnNumber);
			cellDetails.setCellType(CellType.STRING);
			cellDetails.setCellValue(value);

			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				workbook.write(outputStream);
			}
			System.out.println("Successfully written the details to Excel.");
		} catch (Exception e) {
			throw new AutomationException("Error while writing to excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Writes a string value parsed as a number to a cell and formats it as currency (USD).
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param sheetName    The name of the sheet.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @param value        The string value representing a number (e.g., "123.45").
	 * @throws AutomationException if an error occurs during the operation.
	 */
	public static void writeExcelDataToCurrencyCell(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber, String value)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row row = sheet.getRow(rowNumber);
			if (row == null) {
				row = sheet.createRow(rowNumber);
			}

			Cell cell = row.createCell(columnNumber);
			double numericValue = Double.parseDouble(value);
			cell.setCellValue(numericValue);

			CellStyle usdStyle = workbook.createCellStyle();
			DataFormat format = workbook.createDataFormat();
			usdStyle.setDataFormat(format.getFormat("#,##0.00 \"USD\""));
			cell.setCellStyle(usdStyle);

			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				workbook.write(outputStream);
			}
			System.out.println("Successfully written the number to Excel with USD format.");
		} catch (Exception e) {
			throw new AutomationException("Error while writing to excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Writes a value to a cell, handling both numeric and string types.
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param sheetName    The name of the sheet.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @param value        The string value to be written. If it's a valid number, it will be written as numeric; otherwise, as a string.
	 * @throws AutomationException if an error occurs during the write operation.
	 */
	public static void writeExcelDataToIntCell(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber, String value)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				rowDetails = sheetDetails.createRow(rowNumber);
			}

			Cell cellDetails = rowDetails.createCell(columnNumber);

			if (value.matches("-?\\d+(\\.\\d+)?")) {
				cellDetails.setCellType(CellType.NUMERIC);
				cellDetails.setCellValue(Double.parseDouble(value));
			} else {
				cellDetails.setCellType(CellType.STRING);
				cellDetails.setCellValue(value);
			}

			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				workbook.write(outputStream);
			}
			System.out.println("Successfully written the details into Excel.");
		} catch (Exception e) {
			throw new AutomationException("Error while writing to excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Clears all data from a worksheet, leaving only the header row.
	 *
	 * @param fileName The name of the Excel file to clear.
	 * @throws AutomationException if an error occurs during the clearing process.
	 */
	public static void clearExcel(String fileName) throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "ExcelData", fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

			XSSFSheet sheet = workbook.getSheetAt(0);
			if (sheet == null) {
				throw new AutomationException("First sheet not found.");
			}

			// Get header row and iterate through all rows, removing them except for the header.
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();
			for (int i = lastRow; i > firstRow; i--) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					sheet.removeRow(row);
				}
			}

			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				workbook.write(outputStream);
			}
			System.out.println("Successfully cleared the Excel file.");
		} catch (Exception e) {
			throw new AutomationException("Error while clearing the Excel: " + e.getMessage(), e);
		}
	}

	/**
	 * Reads a cell's value from an Excel file located in a specified folder.
	 * This method correctly handles numeric, string, and blank cell types.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param sheetName    The name of the sheet.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @return The formatted string value of the cell.
	 * @throws AutomationException if an error occurs during the read operation.
	 */
	public static String readExcelDataFromFolder(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		String cellValue = "";

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				System.out.println("No data present in the row " + rowNumber + ".");
				return "";
			}

			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				System.out.println("No data present in the cell at coordinates (" + rowNumber + ", " + columnNumber + ").");
				return "";
			}

			DataFormatter formatter = new DataFormatter();
			cellValue = formatter.formatCellValue(cellDetails);
			System.out.println("Data in the required cell is: " + cellValue);

		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
		return cellValue;
	}
    
	/**
	 * Reads the currency code from a numeric cell with a currency format.
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param sheetName    The name of the sheet.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @return The currency symbol or code (e.g., "$", "USD") as a string, or an empty string if not found.
	 * @throws AutomationException if an error occurs during the read operation.
	 */
	public static String readExcelCurrencyFromCell(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				return "";
			}

			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				return "";
			}

			if (cellDetails.getCellType() == CellType.NUMERIC) {
				CellStyle cellStyle = cellDetails.getCellStyle();
				String dataFormatString = cellStyle.getDataFormatString();

				if (dataFormatString.contains("[$")) {
					int startIndex = dataFormatString.indexOf("[$") + 2;
					int endIndex = dataFormatString.indexOf("]", startIndex);
					if (endIndex > startIndex) {
						String currency = dataFormatString.substring(startIndex, endIndex);
						System.out.println("Extracted Currency: " + currency);
						return currency;
					}
				}
				System.out.println("No currency format found in the cell.");
			}
			return "";
		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Reads the RGB color values of a cell's fill foreground color.
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param sheetName    The name of the sheet.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @return A string in the format "RGBvalues:R,G,B" or an empty string if no color is found.
	 * @throws AutomationException if an error occurs during the read operation.
	 */
	public static String readExcelCellColour(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				return "";
			}

			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				return "";
			}

			CellStyle cellStyle = cellDetails.getCellStyle();
			XSSFColor xssfColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
			int[] rgb = getRGBValues(xssfColor);

			System.out.println("RGB values of the cell color: " + rgb[0] + ", " + rgb[1] + ", " + rgb[2]);
			return "RGBvalues:" + rgb[0] + "," + rgb[1] + "," + rgb[2];

		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel color: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Helper method to extract RGB values from an XSSFColor object.
	 *
	 * @param xssfColor The XSSFColor object.
	 * @return An array of 3 integers representing the R, G, and B values.
	 */
	private static int[] getRGBValues(XSSFColor xssfColor) {
		if (xssfColor != null && xssfColor.getRGB() != null) {
			byte[] rgb = xssfColor.getRGB();
			int[] result = new int[3];
			for (int i = 0; i < 3; i++) {
				result[i] = rgb[i] & 0xFF;
			}
			return result;
		} else {
			return new int[]{255, 255, 255}; // Default to white
		}
	}
    
	/**
	 * Modifies the value of the first cell (A1) in the first sheet and saves the file.
	 * This is primarily a test method to ensure file saving works correctly.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @param sheetName  The name of the sheet (not used in the current implementation but kept for signature consistency).
	 * @return The name of the sheet that was modified.
	 * @throws AutomationException if an error occurs during the operation.
	 */
	public static String excelSaveWithSmallChange(String folderName, String fileName, String sheetName)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName + ".xlsx");
		File file = filePath.toFile();

		try (FileInputStream fileInputStream = new FileInputStream(file);
			 XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
			if (row == null) {
				row = sheet.createRow(0);
			}
			XSSFCell cell = row.getCell(0);
			if (cell == null) {
				cell = row.createCell(0);
			}

			cell.setCellValue("New Value");

			try (FileOutputStream fileOut = new FileOutputStream(file)) {
				workbook.write(fileOut);
			}

			System.out.println("File saved successfully with a small change.");
			return sheetName;
		} catch (Exception e) {
			throw new AutomationException("Error while saving the excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Gets the index of the last row containing data in a specified sheet.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @param sheetName  The name of the sheet.
	 * @return The 0-based index of the last row.
	 * @throws AutomationException if an error occurs during the operation.
	 */
	public static int lastRowNumberInExcel(String folderName, String fileName, String sheetName) throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' does not exist.");
			}
			return sheetDetails.getLastRowNum();
		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
	}
    
	/**
	 * Gets the number of cells in the first row of a specified sheet.
	 * This represents the last column index + 1.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @param sheetName  The name of the sheet.
	 * @return The number of columns in the first row.
	 * @throws AutomationException if an error occurs during the operation.
	 */
	public static int lastColumnNumberInExcel(String folderName, String fileName, String sheetName) throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName + ".xlsx");
		File file = filePath.toFile();
		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}
			Row firstRow = sheetDetails.getRow(sheetDetails.getFirstRowNum());
			if (firstRow == null) {
				return 0;
			}
			return firstRow.getLastCellNum();
		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
	}

	/**
	 * Clears the value of a specific cell in an Excel file.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @param sheetName  The name of the sheet.
	 * @param rowNum     The 0-based row index.
	 * @param colNum     The 0-based column index.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void removeCellValue(String folderName, String fileName, String sheetName, int rowNum, int colNum) throws IOException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName + ".xlsx");
		File file = filePath.toFile();
		try (FileInputStream fis = new FileInputStream(file);
			 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet != null) {
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					Cell cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellValue("");
					}
				}
			}
			try (FileOutputStream fos = new FileOutputStream(file)) {
				workbook.write(fos);
			}
			System.out.println("Successfully removed the data in the cell.");
		}
	}

	/**
	 * Gets a specific sheet from an Excel file.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @param sheetName  The name of the sheet.
	 * @return The Sheet object.
	 * @throws IOException if the file cannot be read.
	 */
	public static Sheet getSheetData(String folderName, String fileName, String sheetName) throws IOException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		FileInputStream inputStream = new FileInputStream(file);
		Workbook excelHandlingBook = new XSSFWorkbook(inputStream);
		Sheet sheetDetails = excelHandlingBook.getSheet(sheetName);
		inputStream.close();
		return sheetDetails;
	}

	/**
	 * Gets the number of columns in the first row of a sheet.
	 *
	 * @param sheet The Sheet object to get column count from.
	 * @return The number of columns.
	 * @throws AutomationException if an error occurs.
	 */
	public static int lastColumnNumberInSheet(Sheet sheet) throws AutomationException {
		try {
			Row firstRow = sheet.getRow(sheet.getFirstRowNum());
			return (firstRow != null) ? firstRow.getLastCellNum() : 0;
		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
	}

	/**
	 * Gets the index of the last row in a sheet.
	 *
	 * @param sheet The Sheet object to get row count from.
	 * @return The 0-based index of the last row.
	 * @throws AutomationException if an error occurs.
	 */
	public static int lastRowNumberInSheet(Sheet sheet) throws AutomationException {
		try {
			return sheet.getLastRowNum();
		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
	}

	/**
	 * Copies the data from the first column of one Excel file to another.
	 *
	 * @throws IOException if an I/O error occurs.
	 */
	public static void copyWinmoreIds() throws IOException {
		String sourceFilePath = System.getProperty("user.dir") + "/src/test/resources/DownloadedFiles/Stark_Industries_Limited_Yang_Ming_Marine_TC.xlsx";
		String destinationFilePath = System.getProperty("user.dir") + "/src/test/resources/TestUploadingFiles/Stark Industries Limited_Yang Ming Marine TC.xlsx";

		try (FileInputStream sourceFis = new FileInputStream(sourceFilePath);
			 Workbook sourceWorkbook = new XSSFWorkbook(sourceFis);
			 FileInputStream destFis = new FileInputStream(destinationFilePath);
			 Workbook destWorkbook = new XSSFWorkbook(destFis)) {

			Sheet sourceSheet = sourceWorkbook.getSheetAt(0);
			Sheet destSheet = destWorkbook.getSheetAt(0);

			for (int rowIndex = 0; rowIndex <= sourceSheet.getLastRowNum(); rowIndex++) {
				Row sourceRow = sourceSheet.getRow(rowIndex);
				Row destRow = destSheet.getRow(rowIndex);
				if (destRow == null) {
					destRow = destSheet.createRow(rowIndex);
				}
				if (sourceRow != null) {
					Cell sourceCell = sourceRow.getCell(0);
					Cell destCell = destRow.createCell(0);
					if (sourceCell != null) {
						switch (sourceCell.getCellType()) {
							case STRING:
								destCell.setCellValue(sourceCell.getStringCellValue());
								break;
							case NUMERIC:
								destCell.setCellValue(sourceCell.getNumericCellValue());
								break;
							case BOOLEAN:
								destCell.setCellValue(sourceCell.getBooleanCellValue());
								break;
							default:
								destCell.setCellValue("");
						}
					}
				}
			}

			try (FileOutputStream fos = new FileOutputStream(destinationFilePath)) {
				destWorkbook.write(fos);
			}
			System.out.println("First column copied successfully!");
		}
	}

	/**
	 * Renames a sheet in an Excel file.
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param oldSheetName The current name of the sheet.
	 * @param newSheetName The new name for the sheet.
	 * @throws AutomationException if an I/O error or other exception occurs.
	 */
	public static void renameExcelSheet(String folderName, String fileName, String oldSheetName, String newSheetName) throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream fis = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(fis);
			 FileOutputStream fos = new FileOutputStream(file)) {

			int sheetIndex = workbook.getSheetIndex(oldSheetName);
			if (sheetIndex >= 0) {
				workbook.setSheetName(sheetIndex, newSheetName);
				workbook.write(fos);
				System.out.println("Sheet renamed successfully.");
			} else {
				System.out.println("Sheet not found: " + oldSheetName);
			}
		} catch (IOException e) {
			throw new AutomationException("Error while renaming sheet: " + e.getMessage(), e);
		}
	}

	/**
	 * Checks for the presence of a sheet in an Excel file.
	 *
	 * @param folderName       The name of the folder where the Excel file is located.
	 * @param fileName         The name of the Excel file.
	 * @param sheetNameToCheck The name of the sheet to check.
	 * @return true if the sheet is present, false otherwise.
	 * @throws AutomationException if an error occurs.
	 */
	public static boolean sheetNamePresenceInXlsxFile(String folderName, String fileName, String sheetNameToCheck) throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		boolean sheetPresence = false;

		try (FileInputStream fis = new FileInputStream(file)) {
			Workbook workbook = WorkbookFactory.create(fis);
			sheetPresence = isSheetPresent(workbook, sheetNameToCheck);
			if (sheetPresence) {
				System.out.println("Sheet '" + sheetNameToCheck + "' is present in the Excel file.");
			} else {
				System.out.println("Sheet '" + sheetNameToCheck + "' is not present in the Excel file.");
			}
		} catch (IOException e) {
			throw new AutomationException("Error checking for sheet presence: " + e.getMessage(), e);
		}
		return sheetPresence;
	}

	/**
	 * Helper method to check if a sheet exists in a workbook.
	 *
	 * @param workbook   The Workbook object.
	 * @param sheetName  The name of the sheet to check.
	 * @return true if the sheet is present, false otherwise.
	 */
	private static boolean isSheetPresent(Workbook workbook, String sheetName) {
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Reads a cell's numeric data and formats it as a string.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @param sheetName  The name of the sheet.
	 * @param rowNumber  The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @return The formatted string value of the cell.
	 * @throws AutomationException if an error occurs during the read operation.
	 */
	public static String getFormattedNumericExcelCellData(String folderName, String fileName, String sheetName, int rowNumber, int columnNumber)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		String cellValue = "";

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheet(sheetName);
			if (sheetDetails == null) {
				throw new AutomationException("Sheet '" + sheetName + "' not found.");
			}

			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				return "";
			}

			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				return "";
			}

			DataFormatter dataFormatter = new DataFormatter();
			cellValue = dataFormatter.formatCellValue(cellDetails);
			System.out.println("Data in the required cell is: " + cellValue);

		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel: " + e.getMessage(), e);
		}
		return cellValue;
	}

	/**
	 * Gets the first sheet from an Excel file.
	 *
	 * @param folderName The name of the folder where the Excel file is located.
	 * @param fileName   The name of the Excel file.
	 * @return The first Sheet object.
	 * @throws IOException if the file cannot be read.
	 */
	public static Sheet getFirstSheetData(String folderName, String fileName) throws IOException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheetDetails = workbook.getSheetAt(0);
		inputStream.close();
		return sheetDetails;
	}

	/**
	 * Writes a string value to a specified cell in the first sheet of an Excel file.
	 *
	 * @param folderName   The name of the folder where the Excel file is located.
	 * @param fileName     The name of the Excel file.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @param value        The string value to be written.
	 * @throws AutomationException if an error occurs during the write operation.
	 */
	public static void writeExcelDataInFirstSheet(String folderName, String fileName, int rowNumber, int columnNumber, String value)
			throws AutomationException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();

		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheetDetails = workbook.getSheetAt(0);
			Row rowDetails = sheetDetails.getRow(rowNumber);
			if (rowDetails == null) {
				rowDetails = sheetDetails.createRow(rowNumber);
			}

			Cell cellDetails = rowDetails.createCell(columnNumber);
			cellDetails.setCellType(CellType.STRING);
			cellDetails.setCellValue(value);

			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				workbook.write(outputStream);
			}
			System.out.println("Successfully written the details to the first sheet.");
		} catch (Exception e) {
			throw new AutomationException("Error while writing to excel: " + e.getMessage(), e);
		}
	}

	/**
	 * Finds the 0-based column index of a header based on its name.
	 *
	 * @param sheet      The Sheet object to search in.
	 * @param columnName The name of the column header to find (case-insensitive search).
	 * @return The 0-based column index, or -1 if the header is not found.
	 * @throws AutomationException if an error occurs.
	 */
	public static int getColumnHeaderPositionUsingColumnName(Sheet sheet, String columnName) throws AutomationException {
		try {
			Row headerRow = sheet.getRow(0);
			if (headerRow == null) {
				System.out.println("Header row not found.");
				return -1;
			}
			for (Cell cell : headerRow) {
				if (cell != null && cell.getCellType() == CellType.STRING) {
					String headerValue = cell.getStringCellValue();
					if (headerValue != null && headerValue.toLowerCase().contains(columnName.toLowerCase())) {
						System.out.println("Header '" + headerValue + "' found at position: " + cell.getColumnIndex());
						return cell.getColumnIndex();
					}
				}
			}
			System.out.println("Header '" + columnName + "' not found.");
			return -1;
		} catch (Exception e) {
			throw new AutomationException("Error while reading the excel headers: " + e.getMessage(), e);
		}
	}

	/**
	 * Reads a cell's value from a sheet, handling different cell types and formatting.
	 *
	 * @param sheetData    The Sheet object to read from.
	 * @param rowNumber    The 0-based row index.
	 * @param columnNumber The 0-based column index.
	 * @return The formatted string value of the cell.
	 * @throws AutomationException if an error occurs during the read operation.
	 */
	public static String getFormattedNumericExcelCellData(Sheet sheetData, int rowNumber, int columnNumber) throws AutomationException {
		String cellValue = "";
		try {
			Row rowDetails = sheetData.getRow(rowNumber);
			if (rowDetails == null) {
				System.out.println("No data present in the row " + rowNumber + ".");
				return cellValue;
			}
			Cell cellDetails = rowDetails.getCell(columnNumber);
			if (cellDetails == null) {
				System.out.println("No data present in the cell at coordinates (" + rowNumber + ", " + columnNumber + ").");
				return cellValue;
			}
			DataFormatter dataFormatter = new DataFormatter();
			cellValue = dataFormatter.formatCellValue(cellDetails);
			System.out.println("Data in the required cell is: " + cellValue);
		} catch (Exception e) {
			throw new AutomationException("Error while reading the Excel: " + e.getMessage(), e);
		}
		return cellValue;
	}

	/**
	 * Reads the comment text from a specified cell identified by its column header.
	 *
	 * @param folderName        The name of the folder where the Excel file is located.
	 * @param fileName          The name of the Excel file.
	 * @param sheetName         The name of the sheet.
	 * @param rowNumberOneBased The 1-based row index (Excel-style).
	 * @param columnHeader      The header text of the column (e.g., "Comments").
	 * @return The normalized comment text, or null if the cell or comment does not exist.
	 */
	public static String getCellCommentByHeader(String folderName, String fileName, String sheetName, int rowNumberOneBased, String columnHeader) {
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", folderName, fileName);
		File file = filePath.toFile();
		try (FileInputStream inputStream = new FileInputStream(file);
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) return null;

			Row headerRow = sheet.getRow(0);
			if (headerRow == null) return null;

			int colIndex = -1;
			for (Cell cell : headerRow) {
				if (cell != null && cell.getCellType() == CellType.STRING) {
					String header = cell.getStringCellValue();
					if (header != null && header.trim().equalsIgnoreCase(columnHeader.trim())) {
						colIndex = cell.getColumnIndex();
						break;
					}
				}
			}
			if (colIndex < 0) return null;

			int rowIndex = rowNumberOneBased - 1;
			Row row = sheet.getRow(rowIndex);
			if (row == null) return null;

			Cell cell = row.getCell(colIndex);
			if (cell == null) return null;

			Comment comment = cell.getCellComment();
			if (comment == null) return null;

			String text = comment.getString().getString();
			return text == null ? null : text.replace("\r\n", "\n").trim();
		} catch (IOException e) {
			throw new RuntimeException("Failed to read Excel file: " + e.getMessage(), e);
		}
	}
}