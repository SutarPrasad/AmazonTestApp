package utilityClassPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

public class ExcelFileClass extends BaseClass {
	HashMap<String, String> testValue;
	List<String> excelvalues = new ArrayList<String>();
	FileInputStream file;
	XSSFWorkbook wb;

	/**
	 * Description : Reusable function to initialize excel file.
	 * 
	 * Author Prasad Sutar
	 * 
	 */
	public void excelInitialization() {
		try {
			Properties pro = new Properties();
			pro = propertyFileLoader("src\\main\\resources\\propertiesFiles\\parameterizationPath.properties");
			file = new FileInputStream(pro.getProperty("ExcelFilePath"));
			wb = new XSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * Description : Reusable method for reading data from Excel file extracting
	 * the value and closing workbook at end.
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param testCase--> Used to pass testcase name
	 */
	public void storeExcelValue(String testCase) {
		String searchName = "";

		try {
			excelInitialization();
testValue= new HashMap();
			XSSFSheet sheet = wb.getSheet("Sheet1");
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (int i = 0; i < rowCount + 1; i++) {

				Row row = sheet.getRow(i);
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(testCase)){
					Row row1 = sheet.getRow(i+1);
				for (int j = 1; j < row1.getLastCellNum(); j++) {
						
						searchName = row1.getCell(1).getStringCellValue();
						testValue.put(row1.getCell(0).getStringCellValue(), searchName);
						break;
					}} else {
						continue;
					}
				}

				wb.close();
			
		} catch (Exception e) {
			e.getMessage();
		}

	}

	 /**
	  * Description : To fetch stored excel values in map
	 * 
	 * Author Prasad Sutar
	  * @param key-->Fetches value corresponding to key in hash map
	  *
	  *	  */
	public String getValue(String key) {
		return testValue.get(key);
	}
	/**
	 * Description : Closes workbook
	 * 
	 * Author Prasad Sutar
	 * 
	 * **/
	public void closeWb() throws Exception {
		wb.close();
	}
}