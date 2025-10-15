package com.winmore.testhelper;

import com.winmore.actions.ValidationActionHelper;
import com.winmore.pageinitializer.PageInitializer;
import com.winmore.testingdata.ExcelDataHandler;
import org.testng.Assert;

public class MLCasesHelp extends PageInitializer {

    
    // This method will compare the column header value showing in app with the excel column header value
    // Parameters:
    // MappedColumnShowing: The locator of the column header value showing in app
    // FileName: The name of the excel file (with extension) present in ExcelData folder
    // SheetName: The sheet name in the excel file
    // rowNumber: The row number in the excel sheet (1-based index)
    // columnNumber: The column number in the excel sheet (1-based index)
    
    
public static void columnMappingCheckWithExcel(String MappedColumnShowing, String FileName, String SheetName, int rowNumber, int columnNumber) throws Throwable {

    String MappedColumnValueShowingInApp= validationHelper.getTrimmedText(MappedColumnShowing);
     String excelColumnHeader = ExcelDataHandler.readExcelDataFromFolder("ExcelData", FileName, SheetName, rowNumber, columnNumber);
   Assert.assertEquals(excelColumnHeader, MappedColumnValueShowingInApp, 
       "The value showing in app '" + MappedColumnValueShowingInApp + "' is not matching with excel column header value '" + excelColumnHeader + "'");
 
}

}
