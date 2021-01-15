package utilityClassPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

public class ExcelFileClass extends BaseClass {
	
	FileInputStream file;
	XSSFWorkbook wb;
	/**
	 * Description : Reusable function to initialize excel file.
	 * 
	 * Author Prasad Sutar
	 * 
	 * @throws Exception
	 */
	public void excelInitialization() throws Exception{
		try{
		Properties pro = new Properties();
		pro = propertyFileLoader("C:\\Users\\PRASAD\\eclipse-workspace\\EbayAppAssignment\\src\\main\\java\\propertiesFiles\\parameterizationPath.properties");
		file = new FileInputStream(pro.getProperty("ExcelFilePath"));
		 wb = new XSSFWorkbook(file);
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(),false);
		}}
	
	
	/**
	 * Description : Reusable method for reading data from Excel file 
	 * extracting the value and closing workbook at end.
	 * 
	 * Author Prasad Sutar
	 * @return
	 * @throws Exception
	 */
	public String getExcelValue() throws Exception {
		String searchName = "";
		try{
			excelInitialization();		
		
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		int flag=1;
		for (int i = 1; i < rowCount + 1; i++) {
			i=flag;
			Row row = sheet.getRow(i);
			
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if(row.getCell(j)!=null){
				searchName = row.getCell(j).getStringCellValue();
				flag+=1;
				break;
			}
				else{
					continue;}
				}
		}}catch (Exception e) {
			e.getMessage();
		}wb.close();
		return searchName;
		
		
	}

public void closeWb() throws Exception{
	wb.close();
}}