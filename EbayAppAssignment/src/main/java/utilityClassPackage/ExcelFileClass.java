package utilityClassPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

public class ExcelFileClass extends BaseClass {
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

	 */
	public void storeExcelValue() {
		String searchName = "";

		try {
			excelInitialization();

			XSSFSheet sheet = wb.getSheet("Sheet1");
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (int i = 1; i < rowCount + 1; i++) {

				Row row = sheet.getRow(i);

				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j) != null) {
						searchName = row.getCell(j).getStringCellValue();
						excelvalues.add(searchName);
					} else {
						continue;
					}
				}

				wb.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public String productNameList() {
		int flag = 0;
		String productName = "";
		try{for (int i = 0; i < excelvalues.size(); i++) {
			i = flag;
			productName = excelvalues.get(i);
			flag += 1;
			break;
		}}catch(Exception e){e.getMessage();}
		return productName;
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